package com.companysilas.pokemon.di

import com.companysilas.core.repository.PokemonRepository
import com.companysilas.pokemon.framework.repositoryImpl.PokemonRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<PokemonRepository> { PokemonRepositoryImpl(get()) }

}
