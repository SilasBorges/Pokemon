package com.companysilas.core.repository

import androidx.paging.PagingSource
import com.companysilas.core.domain.model.Pokemon

interface PokemonRepository {

    fun getPokemon(): PagingSource<Int, Pokemon>
}
