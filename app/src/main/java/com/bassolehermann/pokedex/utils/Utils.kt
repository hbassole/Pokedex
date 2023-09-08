package com.bassolehermann.pokedex.utils

import com.bassolehermann.pokedex.R
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.ApiTypeData
import com.bassolehermann.pokedex.features.pokemon.data.models.remote.PokemonTypeData
import com.bassolehermann.pokedex.features.pokemon.domain.entities.ApiType

object Utils {
    fun pokemonColor(type: ApiType) : Int {
        return when(type.name.lowercase()){
            "plante", "insecte" -> R.color.poke_light_teal
            "feu","dragon" -> R.color.poke_light_red
            "eau", "combat", "normal" -> R.color.poke_light_blue
            "electrik", "psy" -> R.color.poke_light_yellow
            "poison", "fantome" -> R.color.poke_light_purple
            "sol", "roche" -> R.color.poke_light_brown
            "ténèbres" -> R.color.poke_black
            else -> return R.color.poke_light_blue
        }
    }
}