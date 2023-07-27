package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.Stats
import io.realm.kotlin.types.RealmObject

class StatsRealm(
    override var HP: Int,
    override var attack: Int,
    override var defense: Int,
    override var special_attack: Int,
    override var special_defense: Int,
    override var speed: Int
): RealmObject, Stats(HP,attack,defense,special_attack,special_defense,speed) {
    constructor():this(0,0,0,0,0,0)
}