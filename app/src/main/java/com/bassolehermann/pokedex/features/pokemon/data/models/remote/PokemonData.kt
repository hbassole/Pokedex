package com.bassolehermann.pokedex.features.pokemon.data.models.remote

import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.google.gson.annotations.Expose
import io.realm.kotlin.types.RealmObject

data class PokemonData(
    override val apiEvolutions: List<ApiEvolutionData>,
    override val apiGeneration: Int,
    override val apiPreEvolution: Any,
    @Expose
    override val apiResistances: List<ApiResistanceData>,
    //override val apiResistancesWithAbilities: List<Any>,
    override val apiTypes: List<ApiTypeData>,
    override val id: Int,
    override val image: String,
    override val name: String,
    override val pokedexId: Int,
   // @Expose
    //override val resistanceModifyingAbilitiesForApi: List<Any>,
    override val slug: String,
    override val sprite: String,
    override val stats: StatsData?
): Pokemon