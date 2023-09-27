package com.companysilas.pokemon.framework.repositoryImpl

import androidx.paging.PagingSource
import com.companysilas.core.domain.model.Pokemon
import com.companysilas.core.repository.PokemonRepository
import com.companysilas.pokemon.framework.paging.PokemonPagingSource
import com.companysilas.pokemon.framework.service.ApiService

class PokemonRepositoryImpl(
    private val apiService: ApiService
) : PokemonRepository {
    override fun getPokemon(): PagingSource<Int, Pokemon> {
        return PokemonPagingSource(apiService)
    }
}
