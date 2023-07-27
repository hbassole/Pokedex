package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiResistance
import io.realm.kotlin.types.RealmObject

open class ApiResistanceRealm(
    override var damage_multiplier: Double,
    override var damage_relation: String,
    override var name: String
):RealmObject, ApiResistance(damage_multiplier,damage_relation,name) {
    constructor():this(0.0,"","")
}