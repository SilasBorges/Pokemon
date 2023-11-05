package com.companysilas.pokemon.presentation.pokemon

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.companysilas.pokemon.databinding.FragmentPokemonBinding
import com.companysilas.pokemon.presentation.detail.DetailActivity
import com.companysilas.pokemon.presentation.pokemon.adapter.PokemonAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding?= null
    private val binding get() = _binding!!

    private val viewModel: PokemonViewModel by viewModel()

    private val pokemonAdapter by lazy {
        PokemonAdapter {
            startActivity(Intent(context, DetailActivity::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.includeViewHomeSuccessState.recyclerPokemon.adapter = pokemonAdapter
        pokemon()
        observeLoadState()
    }

    private fun pokemon() {
        lifecycleScope.launch {
            viewModel.pokemonPagingData().collect() { pagingData ->
                pokemonAdapter.submitData(pagingData)
            }
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            pokemonAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperPokemon.displayedChild = when (loadState.refresh) {
                    is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }

                    is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_POKEMON
                    }

                    is LoadState.Error -> {
                        setShimmerVisibility(false)
                        binding.includeViewHomeErrorState.buttonRetry.setOnClickListener {
                            pokemonAdapter.retry()
                        }
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewHomeLoadingState.loadingPokemon.run {
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else stopShimmer()
        }
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_POKEMON = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }
}
