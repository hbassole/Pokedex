package com.bassolehermann.pokedex.features.pokemon.domain.usecases

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend fun call():Either<Failure,List<Pokemon>?> {
       return pokemonRepository.getAllPokemon();
    }
}




