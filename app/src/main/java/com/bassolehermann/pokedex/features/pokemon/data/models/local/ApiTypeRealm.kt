package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiType
import io.realm.kotlin.types.RealmObject

class ApiTypeRealm(
    override var image: String,
    override var name: String
):RealmObject, ApiType(image,name)