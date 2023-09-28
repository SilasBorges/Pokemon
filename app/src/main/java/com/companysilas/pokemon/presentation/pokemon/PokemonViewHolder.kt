package com.companysilas.pokemon.presentation.pokemon

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

        extractDominantColor(data.url.getPicUrl())
    }

    @Suppress("MagicNumber")
    private fun extractDominantColor(imageUrl: String) {
        Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource).generate { palette ->
                        val defaultColor = ContextCompat.getColor(context, R.color.grey)
                        val dominantColor = palette?.getDominantColor(defaultColor)

                        // Defina a saturação desejada (um valor menor reduzirá a saturação)
                        val saturationFactor = 0.9f // Ajuste conforme necessário

                        // Cor do background personalizado
                        val customBackgroundColor = dominantColor ?: defaultColor

                        // Converte a cor para HSL
                        val hsl = FloatArray(3)
                        ColorUtils.colorToHSL(customBackgroundColor, hsl)

                        // Ajusta a saturação
                        hsl[1] *= saturationFactor

                        // Converte de volta para RGB
                        val saturatedColor = ColorUtils.HSLToColor(hsl)

                        // Obtenha o Drawable do background_recycler
                        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.background_recycler)

                        // Aplicar a cor predominante ofuscada como uma máscara sobre o background_recycler
                        backgroundDrawable?.setTint(saturatedColor)

                        // Defina o Drawable modificado como o plano de fundo
                        binding.root.background = backgroundDrawable
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Implemente o tratamento aqui se necessário.
                }
            })
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
