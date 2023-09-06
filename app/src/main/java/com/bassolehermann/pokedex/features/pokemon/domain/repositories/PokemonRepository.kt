package com.bassolehermann.pokedex.features.pokemon.domain.repositories

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonData
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonTypeData
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.features.pokemon.domain.entities.PokemonType

interface PokemonRepository {
    suspend fun getAllPokemon(): Either<Failure, List<Pokemon>?>
    suspend fun getPokemonById(id:Int): Either<Failure, Pokemon?>
    suspend fun getPokemonByName(name:String): Either<Failure, Pokemon?>
    suspend fun getPokemonTypes(): Either<Failure, List<PokemonType>?>
}