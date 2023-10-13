package com.bassolehermann.pokedex.features.pokemon.domain.usecases

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.core.usecase.UseCase
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonByIdUseCase @Inject constructor(private val pokemonRepository: PokemonRepository):
    UseCase<Pokemon?, Int>() {

    override suspend fun call(params: Int?): Either<Failure, Pokemon?> {
        return pokemonRepository.getPokemonById(id = params!!)
    }
}