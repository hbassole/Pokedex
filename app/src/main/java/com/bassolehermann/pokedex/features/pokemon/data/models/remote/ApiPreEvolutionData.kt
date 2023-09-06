package com.bassolehermann.pokedex.features.pokemon.data.models.remote

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiPreEvolution

data class ApiPreEvolutionData(
    override val name: String,
    override val pokedexIdd: Int):ApiPreEvolution
