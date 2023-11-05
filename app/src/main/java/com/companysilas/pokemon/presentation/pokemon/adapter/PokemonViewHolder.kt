package com.companysilas.pokemon.presentation.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.companysilas.core.domain.model.Pokemon
import com.companysilas.pokemon.R
import com.companysilas.pokemon.databinding.ItemPokemonBinding
import com.companysilas.pokemon.util.getPicUrl

class PokemonViewHolder(
    private val binding: ItemPokemonBinding,
    private val clickListener: (Pokemon) -> Unit,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pokemon) {

        Glide
            .with(context)
            .load(data.url.getPicUrl())
            .fitCenter()
            .into(binding.imagePokemon)

        val name = data.name
        val name2 = name.substring(0, 1).uppercase() + name.substring(1)
        binding.textName.text = name2

        itemView.setOnClickListener {
            clickListener.invoke(data)
        }

    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickListener: (Pokemon) -> Unit
        ): PokemonViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPokemonBinding.inflate(inflater, parent, false)
            return PokemonViewHolder(itemBinding, clickListener, parent.context)
        }
    }
}
