package com.companysilas.pokemon.di

import com.companysilas.pokemon.presentation.pokemon.PokemonViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {

    viewModel {
        PokemonViewModel(get())
    }
}
