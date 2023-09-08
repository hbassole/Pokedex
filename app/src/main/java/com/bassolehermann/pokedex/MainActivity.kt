package com.bassolehermann.pokedex

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.bassolehermann.pokedex.features.pokemon.presentation.pages.HomePage
import com.bassolehermann.pokedex.features.pokemon.presentation.viewModels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var pokemonViewModel: PokemonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomePage(pokemonViewModel)
        }
    }
}



