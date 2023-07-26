package com.bassolehermann.pokedex.features.pokemon.data.models.remote

import com.bassolehermann.pokedex.features.pokemon.domain.entities.PokemonType

data class PokemonTypeData(
    override val englishName: String,
    override val id: Int,
    override val image: String,
    override val name: String
) : PokemonType(englishName,id,image,name)