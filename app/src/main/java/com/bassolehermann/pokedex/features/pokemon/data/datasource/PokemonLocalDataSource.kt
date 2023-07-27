package com.bassolehermann.pokedex.features.pokemon.data.datasource
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonTypeRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface PokemonLocalDataSource {
    suspend fun getLocalAllPokemon(): List<PokemonRealm>?
    suspend fun getLocalPokemonById(id:Int): PokemonRealm?
    suspend fun getLocalPokemonByName(name:String): PokemonRealm?
    suspend fun getLocalPokemonTypes(): List<PokemonTypeRealm>?
    suspend fun deleteLocalPokemon(id:Int):Boolean
    suspend fun deleteLocalPokemonType(id: Int):Boolean
}

@Singleton
class PokemonLocalDataSourceImplement
@Inject constructor(var realm:Realm) : PokemonLocalDataSource {
    override suspend fun getLocalAllPokemon(): List<PokemonRealm>? {
       return withContext(Dispatchers.IO) {
           return@withContext realm.query<PokemonRealm>().find()
       }
    }

    override suspend fun getLocalPokemonById(id: Int): PokemonRealm? {
        return withContext(Dispatchers.IO) {
         return@withContext realm.query<PokemonRealm>("id == $0",id).first().find()
        }
    }

    override suspend fun getLocalPokemonByName(name: String): PokemonRealm? {
       return withContext(Dispatchers.Default) {
            return@withContext realm.query<PokemonRealm>("name == $0",name).first().find()
        }
    }

    override suspend fun getLocalPokemonTypes(): List<PokemonTypeRealm>? {
       return withContext(Dispatchers.Default) {
           return@withContext realm.query<PokemonTypeRealm>().find()
        }
    }

    override suspend fun deleteLocalPokemon(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLocalPokemonType(id: Int): Boolean {
        TODO("Not yet implemented")
    }


}