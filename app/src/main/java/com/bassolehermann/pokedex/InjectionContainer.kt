package com.bassolehermann.pokedex

import android.content.Context
import com.bassolehermann.pokedex.core.constants.Constants.BASE_URL
import com.bassolehermann.pokedex.core.network.NetworkInfoImplement
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonRealm
import com.bassolehermann.pokedex.features.pokemon.data.models.local.PokemonTypeRealm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InjectionContainer {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
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
             .Builder(setOf(PokemonRealm::class, PokemonTypeRealm::class))
             .name("Pokemon.realm")
             .deleteRealmIfMigrationNeeded()
             .schemaVersion(1)
             .build()
        return Realm.open(realmConfig)
    }

    @Provides
    @Singleton
    fun provideNetwork(@ApplicationContext context:Context): NetworkInfoImplement =  NetworkInfoImplement(context)

}