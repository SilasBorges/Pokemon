package com.companysilas.pokemon.framework.service

import com.companysilas.core.domain.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun pokemon(
        @Query("offset") offset: Int?
    ): PokemonResponse
}
