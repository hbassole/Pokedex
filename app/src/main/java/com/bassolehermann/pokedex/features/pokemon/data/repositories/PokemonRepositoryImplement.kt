package com.bassolehermann.pokedex.features.pokemon.data.repositories

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.error.WebError
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.core.network.NetworkInfo
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonDataSource
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonLocalDataSource
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonData
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonTypeData
import com.bassolehermann.pokedex.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImplement
    @Inject constructor(
        networkInfo: NetworkInfo,
        val pokemonDataSource: PokemonDataSource,
        pokemonLocalDataSource: PokemonLocalDataSource
        ): PokemonRepository {
    val networkInfo: NetworkInfo = networkInfo
    val pokemonLocalDataSource: PokemonLocalDataSource = pokemonLocalDataSource
    override suspend fun getAllPokemon(): Either<Failure, List<PokemonData>> {
        return if (networkInfo.isConnected()) {
            try {
                val response = pokemonDataSource.getAllPokemon()
                if (response.isSuccessful) {
                    Either.Right(response.body()!!)
                } else {
                    Either.Left(WebError(response.code(),response.message()))
                }
            } catch (exception:Throwable){
                Either.Left(Failure(null,exception.message))
            }
        } else {
            Either.Left(Failure(0,"Veuillez verifier votre connexion internet puis réessayer"))
        }
    }

    override suspend fun getPokemonById(id: Int): Either<Failure, PokemonData> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonByName(name: String): Either<Failure, PokemonData> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonTypes(): Either<Failure, List<PokemonTypeData>> {
        TODO("Not yet implemented")
    }

}