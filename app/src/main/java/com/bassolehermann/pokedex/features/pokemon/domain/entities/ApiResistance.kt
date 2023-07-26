package com.bassolehermann.pokedex.features.pokemon.domain.entities

open class ApiResistance(
    open val damage_multiplier: Double,
    open val damage_relation: String,
    open val name: String
)