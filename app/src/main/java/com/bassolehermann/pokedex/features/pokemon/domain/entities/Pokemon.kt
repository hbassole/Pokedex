package com.bassolehermann.pokedex.features.pokemon.domain.entities

open class Pokemon(
    open val apiEvolutions: List<ApiEvolution>,
    open val apiGeneration: Int,
    open val apiPreEvolution: String,
    open val apiResistances: List<ApiResistance>,
    open val apiResistancesWithAbilities: List<Any>,
    open val apiTypes: List<ApiType>,
    open val id: Int,
    open val image: String,
    open val name: String,
    open val pokedexId: Int,
    open val resistanceModifyingAbilitiesForApi: List<Any>,
    open val slug: String,
    open val sprite: String,
    open val stats: Stats
)