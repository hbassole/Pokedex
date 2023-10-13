package com.bassolehermann.pokedex.features.pokemon.domain.usecases

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.core.usecase.UseCase
import com.bassolehermann.pokedex.features.pokemon.domain.entities.PokemonType
import com.bassolehermann.pokedex.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonTypeUseCase @Inject constructor(private  val pokemonRepository: PokemonRepository) : UseCase<List<PokemonType>?,Int>() {
    override suspend fun call(params: Int?): Either<Failure, List<PokemonType>?> {

        return pokemonRepository.getPokemonTypes()
    }
}