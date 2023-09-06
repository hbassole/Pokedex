package com.bassolehermann.pokedex.features.pokemon.data.models.remote

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiType

data class ApiTypeData(
     override val image: String,
     override val name: String
): ApiType