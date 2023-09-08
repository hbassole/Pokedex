package com.bassolehermann.pokedex.features.pokemon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bassolehermann.pokedex.R

@Composable
fun LoaderPokeDex(degreeRotate:Float) {
    Image(
        painter = painterResource(id = R.drawable.pokeball),
        contentDescription = "loader",
        modifier = Modifier
            .rotate(degreeRotate)
            .height(120.dp)
            .width(120.dp)
    )
}