package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.PokemonType
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class PokemonTypeRealm(
    override val englishName: String,
    override val id: Int,
    override val image: String,
    override val name: String
):RealmObject, PokemonType(englishName,id,image,name) {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    constructor():this("",0,"","")
}