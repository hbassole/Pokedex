package com.bassolehermann.pokedex.features.pokemon.domain.usecases

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.core.usecase.UseCase
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonByNameUseCase @Inject constructor(private  val  pokemonRepository: PokemonRepository): UseCase<Pokemon?, String>() {
    override suspend fun call(params: String?): Either<Failure, Pokemon?> {
        return pokemonRepository.getPokemonByName(name = params!!)
    }
}