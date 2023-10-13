package com.bassolehermann.pokedex.features.pokemon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bassolehermann.pokedex.R
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.utils.Utils
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokedexCard(pokemon: Pokemon) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(colorResource(id = Utils.pokemonColor(pokemon.apiTypes.first())))
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokeball),
            contentDescription = "",
            modifier = Modifier
                .height(110.dp)
                .width(110.dp)
                .absoluteOffset(70.dp, 40.dp),
            colorFilter = ColorFilter.tint(Color.White.copy(0.4f))
        )
        GlideImage(
            model = pokemon.image,
            contentDescription = pokemon.name,
            Modifier
                .height(90.dp)
                .width(90.dp)
                .absoluteOffset(80.dp, 50.dp),
        )
        Column(Modifier.offset(10.dp, 10.dp)) {
            Text(
                text = "${pokemon.name}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Box(modifier = Modifier.height(10.dp))
             for (type in pokemon.apiTypes){
                 Text(
                     text = type.name,
                     color = Color.White,
                     fontWeight = FontWeight.Bold,
                     fontSize = 11.sp,
                     modifier = Modifier
                         .clip(shape = RoundedCornerShape(30.dp))
                         .background(Color.White.copy(0.2f))
                         .padding(8.dp, 0.dp, 8.dp, 3.dp)
                 )
                 Box(modifier = Modifier.height(10.dp))
             }
        }
    }
}