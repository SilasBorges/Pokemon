package com.companysilas.pokemon.presentation.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.companysilas.core.domain.model.Pokemon
import com.companysilas.core.usecase.PokemonUseCase
import kotlinx.coroutines.flow.Flow

class PokemonViewModel(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {

    fun pokemonPagingData(): Flow<PagingData<Pokemon>> {
        return pokemonUseCase(
            PokemonUseCase.Params
        ).cachedIn(viewModelScope)
    }
}
