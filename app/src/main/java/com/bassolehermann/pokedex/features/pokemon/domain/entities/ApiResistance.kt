package com.bassolehermann.pokedex.features.pokemon.domain.entities

interface ApiResistance{
    val damage_multiplier: Double
    val damage_relation: String
    val name: String
}