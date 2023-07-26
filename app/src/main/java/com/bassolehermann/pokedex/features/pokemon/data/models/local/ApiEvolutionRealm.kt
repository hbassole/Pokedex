package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiEvolution
import io.realm.kotlin.types.RealmObject

open class ApiEvolutionRealm(
    override var name: String,
    override var pokedexId: Int
):RealmObject, ApiEvolution(name,pokedexId)