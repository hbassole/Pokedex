package com.bassolehermann.pokedex.features.pokemon.data.datasource
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonData
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonTypeData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface PokemonDataSource {
    @GET("/api/v1/pokemon")
    suspend fun getAllPokemon(): Response<List<PokemonData>>

    @GET("api/v1/pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id:Int): Response<PokemonData>

    @GET("/api/v1/pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name:String): Response<PokemonData>

    @GET("/api/v1/types")
    suspend fun getPokemonTypes(): Response<List<PokemonTypeData>>
}

@Singleton
class PokemonDataSourceImplement
@Inject constructor(retrofit: Retrofit) : PokemonDataSource {

    private val retrofitApi: PokemonDataSource by lazy { retrofit.create(PokemonDataSource::class.java) }

    override suspend fun getAllPokemon(): Response<List<PokemonData>> = retrofitApi.getAllPokemon()

    override suspend fun getPokemonById(id: Int): Response<PokemonData> = retrofitApi.getPokemonById(id)

    override suspend fun getPokemonByName(name: String): Response<PokemonData> = retrofitApi.getPokemonByName(name)

    override suspend fun getPokemonTypes(): Response<List<PokemonTypeData>> = retrofitApi.getPokemonTypes()
}