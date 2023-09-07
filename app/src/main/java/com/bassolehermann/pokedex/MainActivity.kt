package com.bassolehermann.pokedex

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bassolehermann.pokedex.features.pokemon.presentation.viewModels.PokemonViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomePage(viewModel: PokemonViewModel) {
    LaunchedEffect(key1 = "getPokemon", block = {
        viewModel.getPokemons()
    })

    if (viewModel.isLoading.observeAsState().value != null && !viewModel.isLoading.observeAsState().value!!) {
        if (viewModel.pokemonList.value.isNullOrEmpty()) {
            Text(text = "Aucun element disponible")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                items(viewModel.pokemonList.value!!) { pokemon ->

                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color.Cyan)

                    ) {
                        GlideImage(
                            model = pokemon.image,
                            contentDescription = pokemon.name,
                            Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .absoluteOffset(70.dp, 50.dp),
                        )
                        Column(Modifier.offset(10.dp, 10.dp)) {
                            Text(text = "${pokemon.name}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp )

                        }

                    }

                }
            }
        }
    } else {
        CircularProgressIndicator()
    }


}

