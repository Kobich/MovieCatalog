package com.moviecatalog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviecatalog.core.ui.theme.MovieCatalogTheme
import com.moviecatalog.ui.cards.api.CardsUiFeature
import com.moviecatalog.ui.details.api.DetailsUiFeature
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val detailsUiFeature by inject<DetailsUiFeature>()
    private val cardsUiFeature by inject<CardsUiFeature>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCatalogTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "cards"
                ) {
                    composable("cards") {
                        cardsUiFeature.Content(navController)
                    }

                    composable(
                        route = "cards/{cardId}",
                        arguments = listOf(navArgument("cardId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId =
                            backStackEntry.arguments?.getInt("cardId") ?: return@composable
                        detailsUiFeature.Content(navController, movieId)
                    }

                    composable(
                        route = "details/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId =
                            backStackEntry.arguments?.getInt("movieId") ?: return@composable
                        detailsUiFeature.Content(navController, movieId)
                    }
                }
            }
        }
    }
}





