package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiPreEvolution
import io.realm.kotlin.types.RealmObject

open class ApiPreEvolutionRealm(
    override var name: String,
    override var pokedexIdd: Int) : RealmObject, ApiPreEvolution {
    constructor() : this("",0)
}