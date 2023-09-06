package com.bassolehermann.pokedex.features.pokemon.data.models.remote

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiEvolution

data class ApiEvolutionData(
    override val name: String,
    override val pokedexId: Int
): ApiEvolution