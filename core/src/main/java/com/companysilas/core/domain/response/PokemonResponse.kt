package com.companysilas.core.domain.response

import com.companysilas.core.domain.model.Pokemon

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Boolean,
    val results: List<Pokemon>
)
