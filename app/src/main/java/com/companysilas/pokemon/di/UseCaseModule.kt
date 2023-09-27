package com.companysilas.pokemon.di

import com.companysilas.core.usecase.PokemonUseCase
import com.companysilas.core.usecase.PokemonUseCaseImpl
import org.koin.dsl.module

val useCase = module {

    single<PokemonUseCase> { PokemonUseCaseImpl(get())}

}
