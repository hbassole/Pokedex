package com.bassolehermann.pokedex.features.pokemon.domain.repositories

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonData
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonTypeData

interface PokemonRepository {
    suspend fun getAllPokemon(): Either<Failure, List<PokemonData>>
    suspend fun getPokemonById(id:Int): Either<Failure, PokemonData>
    suspend fun getPokemonByName(name:String): Either<Failure, PokemonData>
    suspend fun getPokemonTypes(): Either<Failure, List<PokemonTypeData>>
}