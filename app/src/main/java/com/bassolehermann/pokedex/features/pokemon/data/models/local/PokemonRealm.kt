package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class PokemonRealm(
    override var apiEvolutions: RealmList<ApiEvolutionRealm>,
    override var apiGeneration: Int,
    @Ignore
    override var apiPreEvolution: ApiPreEvolutionRealm?,
    override var apiResistances: RealmList<ApiResistanceRealm>,
   // @Ignore
    //override var apiResistancesWithAbilities: List<Any>,
    override var apiTypes: RealmList<ApiTypeRealm>,
    override var id: Int,
    override var image: String,
    override var name: String,
    override var pokedexId: Int,
    //@Ignore
    //override var resistanceModifyingAbilitiesForApi: List<Any>,
    override var slug: String,
    override var sprite: String,
    override var stats: StatsRealm?
): RealmObject, Pokemon {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    constructor(): this(
        realmListOf(),
        0,
        ApiPreEvolutionRealm(),
        realmListOf(),
        realmListOf(),
        //realmListOf(),
        0,
        "",
        "",
        0,
       // listOf(),
        "",
        "",
        StatsRealm()
    )
}