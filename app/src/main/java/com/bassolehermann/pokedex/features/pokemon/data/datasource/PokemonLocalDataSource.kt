package com.bassolehermann.pokedex.features.pokemon.data.datasource
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonTypeRealm
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.ext.insert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface PokemonLocalDataSource {
    suspend fun addPokemon(pokemon:PokemonRealm)
    suspend fun addAllPokemon(pokemon:List<PokemonRealm>)
    suspend fun getLocalAllPokemon(): List<PokemonRealm>?
    suspend fun getLocalPokemonById(id:Int): PokemonRealm?
    suspend fun getLocalPokemonByName(name:String): PokemonRealm?
    suspend fun getLocalPokemonTypes(): List<PokemonTypeRealm>?
    suspend fun deleteLocalPokemon(id:Int):Boolean
    suspend fun deleteLocalPokemonType(id: Int):Boolean
}

class PokemonLocalDataSourceImplement
@Inject constructor(private val realm:Realm) : PokemonLocalDataSource {
    override suspend fun addPokemon(pokemon: PokemonRealm) {
        return withContext(Dispatchers.IO){
            return@withContext realm.write {
                copyToRealm(pokemon)
            }
        }
    }

    override suspend fun addAllPokemon(pokemon: List<PokemonRealm>){
       for (pokemonReal in pokemon){
           addPokemon(pokemonReal)
       }
    }

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
        return  withContext(Dispatchers.Default){
           var isSuccess:Boolean = false

            realm.write {
                var pokemon =  realm.query<PokemonRealm>("id == $0",id).first().find()
                isSuccess = if (pokemon != null) {
                    delete(pokemon)
                    true;
                } else {
                    false
                }
            }
            return@withContext isSuccess
        }
    }

    override suspend fun deleteLocalPokemonType(id: Int): Boolean {
        return  withContext(Dispatchers.Default){
            var isSuccess:Boolean = false
            realm.write {
                var pokemonType =  realm.query<PokemonTypeRealm>("id == $0",id).first().find()
                isSuccess = if (pokemonType != null) {
                    delete(pokemonType)
                    true;
                } else {
                    false
                }
            }
            return@withContext isSuccess
        }
    }


}