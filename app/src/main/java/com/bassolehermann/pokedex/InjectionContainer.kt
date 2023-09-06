package com.bassolehermann.pokedex

import android.content.Context
import com.bassolehermann.pokedex.core.constants.Constants.BASE_URL
import com.bassolehermann.pokedex.core.network.NetworkInfo
import com.bassolehermann.pokedex.core.network.NetworkInfoImplement
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonDataSource
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonDataSourceImplement
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonLocalDataSource
import com.bassolehermann.pokedex.features.pokemon.data.datasource.PokemonLocalDataSourceImplement
import com.bassolehermann.pokedex.features.pokemon.data.models.local.ApiEvolutionRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.ApiPreEvolutionRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.ApiResistanceRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.ApiTypeRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonTypeRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.StatsRealm
import com.bassolehermann.pokedex.features.pokemon.data.repositories.PokemonRepositoryImplement
import com.bassolehermann.pokedex.features.pokemon.domain.usecases.GetPokemonUseCase
import com.bassolehermann.pokedex.features.pokemon.presentation.viewModels.PokemonViewModel

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InjectionContainer {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val okHttpClientBuilder:OkHttpClient.Builder = OkHttpClient().newBuilder()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRealM(): Realm {
         val realmConfig: RealmConfiguration = RealmConfiguration
             .Builder(setOf(PokemonRealm::class, PokemonTypeRealm::class, ApiEvolutionRealm::class, ApiResistanceRealm::class, ApiTypeRealm::class, StatsRealm::class, ApiPreEvolutionRealm::class))
             .name("Pokemon.realm")
             .deleteRealmIfMigrationNeeded()

             .schemaVersion(1)
             .build()
        return Realm.open(realmConfig)
    }

    @Provides
    @Singleton
    fun providePokemonDataSourceImplement(retrofit: Retrofit) = PokemonDataSourceImplement(retrofit = retrofit)
    @Provides
    @Singleton
    fun providePokemonLocalDataSourceImplement(realm: Realm) = PokemonLocalDataSourceImplement(realm = realm)

    @Provides
    @Singleton
    fun providePokemonRepositoryImplement(networkInfo: NetworkInfoImplement,pokemonDataSource: PokemonDataSourceImplement, pokemonLocalDataSource: PokemonLocalDataSourceImplement) = PokemonRepositoryImplement(networkInfo = networkInfo, pokemonDataSource = pokemonDataSource, pokemonLocalDataSource = pokemonLocalDataSource)
    @Provides
    @Singleton
    fun providePokemonUseCase(pokemonRepositoryImplement: PokemonRepositoryImplement) = GetPokemonUseCase(pokemonRepository = pokemonRepositoryImplement)

    @Provides
    @Singleton
    fun provideNetwork(@ApplicationContext context: Context): NetworkInfoImplement =  NetworkInfoImplement(context = context)


    @Provides
    @Singleton
    fun providePokemonViewModel(useCase: GetPokemonUseCase) = PokemonViewModel(getPokemonUseCase = useCase)

}