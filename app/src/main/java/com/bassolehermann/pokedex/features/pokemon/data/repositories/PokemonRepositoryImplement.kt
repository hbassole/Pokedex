package com.bassolehermann.pokedex.features.pokemon.data.repositories

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.error.WebError
import com.bassolehermann.pokedex.core.functionals.Either
import com.bassolehermann.pokedex.core.network.NetworkInfo
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonDataSource
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonLocalDataSource
import com.bassolehermann.pokedex.features.pokemon.data.models.local.ApiEvolutionRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.ApiResistanceRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.ApiTypeRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonData
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonTypeData
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.features.pokemon.domain.entities.PokemonType
import com.bassolehermann.pokedex.features.pokemon.domain.repositories.PokemonRepository
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import javax.inject.Inject

class PokemonRepositoryImplement
    @Inject constructor(
        private val networkInfo: NetworkInfo,
        private val pokemonDataSource: PokemonDataSource,
        private val pokemonLocalDataSource: PokemonLocalDataSource
        ): PokemonRepository {
    override suspend fun getAllPokemon(): Either<Failure, List<Pokemon>?> {
        return if (networkInfo.isConnected()) {
            try {
                val response = pokemonDataSource.getAllPokemon()
                if (response.isSuccessful) {
                    var pokemons : List<PokemonData> = response.body()!!
                    var pokemonsRealm = pokemonsToPokemonRealms(pokemons)
                    //pokemonLocalDataSource.addAllPokemon(pokemonsRealm)
                    Either.Right(pokemons)
                } else {
                    Either.Left(WebError(response.code(),response.message()))
                }
            } catch (exception:Throwable){
                Either.Left(Failure(null,exception.message))
            }
        } else {
            try {
                val response = pokemonLocalDataSource.getLocalAllPokemon()
                Either.Right(response)
            } catch (e:Throwable){
                Either.Left(Failure(null,e.message))
            }

        }
    }

    override suspend fun getPokemonById(id: Int): Either<Failure, Pokemon?> {
        return if (networkInfo.isConnected()) {
            try {
                val response = pokemonDataSource.getPokemonById(id = id)
                if (response.isSuccessful) {
                    Either.Right(response.body()!!)
                } else {
                    Either.Left(WebError(response.code(),response.message()))
                }
            } catch (e:Throwable) {
                Either.Left(Failure(null,e.message))
            }
        } else {
            try {
                val response = pokemonLocalDataSource.getLocalPokemonById(id=id)
                Either.Right(response)
            } catch (e:Throwable) {
                Either.Left(Failure(0,"Aucune connexion internet ,veuillez vous connecter et réessayer"))
            }
        }
    }

    override suspend fun getPokemonByName(name: String): Either<Failure, Pokemon?> {
        return if (networkInfo.isConnected()) {
            try {
                var response = pokemonDataSource.getPokemonByName(name = name)
                if (response.isSuccessful) {
                    Either.Right(response.body()!!)
                } else {
                    Either.Left(WebError(response.code(),response.message()))
                }
            }catch (e:Throwable) {
                Either.Left(Failure(null,e.message))
            }
        } else {
            try {
                var response = pokemonLocalDataSource.getLocalPokemonByName(name = name)
                Either.Right(response)
            } catch (e:Throwable) {
                Either.Left(Failure(0,"Aucune connexion internet ,veuillez vous connecter et réessayer"))
            }
        }
    }

    override suspend fun getPokemonTypes(): Either<Failure, List<PokemonType>?> {
        return if (networkInfo.isConnected()) {
            try {
                var response = pokemonDataSource.getPokemonTypes()

                if (response.isSuccessful) {
                    Either.Right(response.body()!!)
                } else {
                    Either.Left(WebError(response.code(),response.message()))
                }
            }catch (e:Throwable) {
                Either.Left(Failure(null,e.message))
            }
        } else {
            try {
                var response = pokemonLocalDataSource.getLocalPokemonTypes()
                Either.Right(response)
            } catch (e:Throwable) {
                Either.Left(Failure(0,"Aucune connexion internet ,veuillez vous connecter et réessayer"))
            }
        }
    }

    fun pokemonsToPokemonRealms(pokemonsData: List<PokemonData>):RealmList<PokemonRealm> {
       return pokemonsData.map { pokemonData ->
            PokemonRealm().apply {
                id = pokemonData.id
                image = pokemonData.image
                name = pokemonData.name
                pokedexId = pokemonData.pokedexId
                slug = pokemonData.slug
                sprite = pokemonData.sprite
                apiGeneration = pokemonData.apiGeneration
                apiTypes = pokemonData.apiTypes.map { apiTypeData ->

                    ApiTypeRealm().apply {
                        this.name = apiTypeData.name
                        this.image = apiTypeData.image
                    } }.toRealmList()

                apiEvolutions = pokemonData.apiEvolutions.map { apiEvolutionData ->
                    ApiEvolutionRealm().apply {
                        this.name = apiEvolutionData.name
                        this.pokedexId = apiEvolutionData.pokedexId
                    }
                }.toRealmList()

                apiResistances = pokemonData.apiResistances .map { apiResistance ->
                    ApiResistanceRealm().apply {
                        this.name = apiResistance.name
                        this.damage_multiplier = apiResistance.damage_multiplier
                        this.damage_relation = apiResistance.damage_relation
                    }
                }.toRealmList()

            } }.toRealmList()
    }
}