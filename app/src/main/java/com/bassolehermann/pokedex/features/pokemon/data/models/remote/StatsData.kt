package com.bassolehermann.pokedex.features.pokemon.data.models.remote

import com.bassolehermann.pokedex.features.pokemon.domain.entities.Stats

data class StatsData(
    override val HP: Int,
    override val attack: Int,
    override val defense: Int,
    override val special_attack: Int,
    override val special_defense: Int,
    override val speed: Int
): Stats(HP,attack,defense,special_attack,special_defense,speed)