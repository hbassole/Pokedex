package com.bassolehermann.pokedex

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bassolehermann.pokedex.features.pokemon.presentation.pages.HomePage
import com.bassolehermann.pokedex.features.pokemon.presentation.viewModels.PokemonViewModel

@Composable
fun AppNavController(pokemonViewModel: PokemonViewModel){
    var navigatorController = rememberNavController()
    NavHost(navController = navigatorController, startDestination = Router.homePageRoute){

        composable(Router.homePageRoute){
            HomePage(navController = navigatorController ,viewModel = pokemonViewModel)
        }
        composable("${Router.detailPageRoute}/{pokemon}",
            listOf(
                navArgument("pokemon"){
                    type = NavType.StringType
                }
            )
        ){ navBackStackEntry ->

        }

    }
}