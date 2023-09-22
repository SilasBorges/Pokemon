package com.companysilas.pokemon.di

import com.companysilas.pokemon.framework.di.serviceModule

val listModules =
    listOf(coroutinesModules, repositoryModule, useCase, viewModelModule, serviceModule)
