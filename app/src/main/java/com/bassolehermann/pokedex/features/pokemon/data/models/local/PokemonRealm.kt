package com.bassolehermann.pokedex.features.pokemon.data.models.local

import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class PokemonRealm(
    override var apiEvolutions: List<ApiEvolutionRealm>,
    override var apiGeneration: Int,
    override var apiPreEvolution: String,
    override var apiResistances: List<ApiResistanceRealm>,
    override var apiResistancesWithAbilities: List<Any>,
    override var apiTypes: List<ApiTypeRealm>,
    @PrimaryKey
    override var id: Int,
    override var image: String,
    override var name: String,
    override var pokedexId: Int,
    override var resistanceModifyingAbilitiesForApi: List<Any>,
    override var slug: String,
    override var sprite: String,
    override var stats: StatsRealm
): RealmObject, Pokemon(apiEvolutions,apiGeneration,apiPreEvolution,apiResistances,apiResistancesWithAbilities,apiTypes,id,image,name,pokedexId,resistanceModifyingAbilitiesForApi,slug,sprite,stats)