package com.bassolehermann.pokedex.features.pokemon.presentation.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bassolehermann.pokedex.R
import com.bassolehermann.pokedex.features.pokemon.presentation.components.LoaderPokeDex
import com.bassolehermann.pokedex.features.pokemon.presentation.components.PokedexCard
import com.bassolehermann.pokedex.features.pokemon.presentation.viewModels.PokemonViewModel
import com.bassolehermann.pokedex.utils.Utils
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import javax.inject.Inject

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomePage(viewModel: PokemonViewModel) {
    var currentRotation by remember { mutableFloatStateOf(0f) }
    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(key1 = "homePage", block = {
        viewModel.getPokemons()
        rotation.animateTo(
            targetValue = currentRotation + 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation = value
        }


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
                    PokedexCard(pokemon = pokemon)
                }
            }
        }
    } else {
        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(), contentAlignment = Alignment.Center) {
            LoaderPokeDex(degreeRotate = rotation.value)
        }
    }
}
