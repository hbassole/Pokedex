package com.bassolehermann.pokedex.features.pokemon.data.models.remote

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiResistance

data class ApiResistanceData(
    override val damage_multiplier: Double,
    override val damage_relation: String,
    override val name: String
): ApiResistance(damage_multiplier,damage_relation,name)