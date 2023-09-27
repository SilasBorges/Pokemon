package com.companysilas.pokemon.presentation.pokemon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.companysilas.pokemon.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: PokemonViewModel by viewModel()

    private val pokemonAdapter by lazy {
        PokemonAdapter{}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        pokemon()
        initView()
        setContentView(binding.root)

    }

    private fun initView() {
        binding.recyclerPokemon.adapter = pokemonAdapter
    }

    private fun pokemon() {
        lifecycleScope.launch {
            viewModel.pokemonPagingData().collect() { pagingData ->
                pokemonAdapter.submitData(pagingData)
            }
        }
    }
}
