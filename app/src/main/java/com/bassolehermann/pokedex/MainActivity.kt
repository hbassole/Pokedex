package com.bassolehermann.pokedex

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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

@Composable
fun HomePage(viewModel: PokemonViewModel){
    LaunchedEffect(key1 = "getPokemon", block = {
        viewModel.getPokemons()
    } )

    if(viewModel.isLoading.observeAsState().value != null && !viewModel.isLoading.observeAsState().value!!) {
        if (viewModel.pokemonList.value.isNullOrEmpty()) {
            Text(text = "Aucun element disponible")
        } else {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                List(size = viewModel.pokemonList.value!!.size, init = {
                    Text(text = "$it")
                })
            }
        }
    } else {
        CircularProgressIndicator()
    }


}