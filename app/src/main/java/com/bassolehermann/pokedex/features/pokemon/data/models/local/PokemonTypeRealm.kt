package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.PokemonType
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class PokemonTypeRealm(
    override var englishName: String,
    override var id: Int,
    override var image: String,
    override var name: String
):RealmObject, PokemonType {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    constructor():this("",0,"","")
}