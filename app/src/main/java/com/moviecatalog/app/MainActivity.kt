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
import com.moviecatalog.ui.card.api.CardDetailsUiFeature
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val cardDetailsUiFeature by inject<CardDetailsUiFeature>()
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
                        route = "card/{imtID}",
                        arguments = listOf(navArgument("imtID") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val imtID =
                            backStackEntry.arguments?.getLong("imtID") ?: return@composable
                        cardDetailsUiFeature.Content(navController, imtID)
                    }
                }
            }
        }
    }
}
