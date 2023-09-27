package com.companysilas.pokemon.presentation.pokemon

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.companysilas.core.domain.model.Pokemon
import com.companysilas.pokemon.util.extractId

class PokemonAdapter(
    private val adapterOnClick: (Pokemon) -> Unit
) : PagingDataAdapter<Pokemon, PokemonViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(
            parent,
            adapterOnClick
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(
                oldItem: Pokemon,
                newItem: Pokemon
            ): Boolean {
                return oldItem.url.extractId() == newItem.url.extractId()
            }

            override fun areContentsTheSame(
                oldItem: Pokemon,
                newItem: Pokemon
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
