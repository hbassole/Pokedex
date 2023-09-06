package com.bassolehermann.pokedex.features.pokemon.domain.usecases

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.core.usecase.UseCase
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.features.pokemon.domain.entities.PokemonType
import com.bassolehermann.pokedex.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend fun call():Either<Failure,List<Pokemon>?> {
       return pokemonRepository.getAllPokemon();
    }
}
class GetPokemonByIdUseCase @Inject constructor(private val pokemonRepository: PokemonRepository):
    UseCase<Pokemon?, Int>() {
    override suspend fun call(params: Int):Either<Failure,Pokemon?> {
        return pokemonRepository.getPokemonById(id = params)
    }
}

class GetPokemonByNameUseCase @Inject constructor(private  val  pokemonRepository: PokemonRepository): UseCase<Pokemon?,String>() {
    override suspend fun call(params: String): Either<Failure, Pokemon?> {
        return pokemonRepository.getPokemonByName(name = params)
    }
}

class GetPokemonTypeUseCase @Inject constructor(private  val pokemonRepository: PokemonRepository) {
    suspend fun call():Either<Failure,List<PokemonType>?> {
        return pokemonRepository.getPokemonTypes()
    }
}