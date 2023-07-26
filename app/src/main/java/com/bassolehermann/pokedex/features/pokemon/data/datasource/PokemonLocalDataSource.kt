package com.bassolehermann.pokedex.features.pokemon.data.datasource
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonData
import io.realm.kotlin.Realm
import javax.inject.Inject
import javax.inject.Singleton

interface PokemonLocalDataSource {
    suspend fun getLocalAllPokemon(): List<PokemonData>
    suspend fun getLocalPokemonById(id:Int): PokemonData
    suspend fun getLocalPokemonByName(name:String): PokemonData
}

@Singleton
class PokemonLocalDataSourceImplement
@Inject constructor(var realm:Realm) : PokemonLocalDataSource {
    override suspend fun getLocalAllPokemon(): List<PokemonData> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocalPokemonById(id: Int): PokemonData {
        TODO("Not yet implemented")
    }

    override suspend fun getLocalPokemonByName(name: String): PokemonData {
        TODO("Not yet implemented")
    }

}