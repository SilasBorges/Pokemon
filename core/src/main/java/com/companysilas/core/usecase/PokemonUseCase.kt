package com.companysilas.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.companysilas.core.domain.model.Pokemon
import com.companysilas.core.repository.PokemonRepository
import com.companysilas.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {

    operator fun invoke(params: Params): Flow<PagingData<Pokemon>>

    object Params
}

class PokemonUseCaseImpl(
    private val repository: PokemonRepository
) : PagingUseCase<PokemonUseCase.Params, Pokemon>(), PokemonUseCase {
    override fun createFlowObservable(params: PokemonUseCase.Params): Flow<PagingData<Pokemon>> {
        val pagingSource = repository.getPokemon()
        return Pager(config = getPagerConfig()) {
            pagingSource
        }.flow
    }

    private fun getPagerConfig() = PagingConfig(
        pageSize = 20
    )
}
