package com.bassolehermann.pokedex.features.pokemon.domain.entities

open class Stats(
    open val HP: Int,
    open val attack: Int,
    open val defense: Int,
    open val special_attack: Int,
    open val special_defense: Int,
    open val speed: Int
)