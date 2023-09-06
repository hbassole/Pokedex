package com.bassolehermann.pokedex.features.pokemon.domain.entities

import java.lang.reflect.Array

interface Pokemon{

     val apiEvolutions: List<ApiEvolution>
     val apiGeneration: Int
     val apiPreEvolution: Any?
     val apiResistances: List<ApiResistance>
     //val apiResistancesWithAbilities: Array
     val apiTypes: List<ApiType>
     val id: Int
     val image: String
     val name: String
     val pokedexId: Int
     //val resistanceModifyingAbilitiesForApi: Array
     val slug: String
     val sprite: String
     val stats: Stats?
}