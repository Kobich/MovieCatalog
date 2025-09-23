package com.moviecatalog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviecatalog.core.ui.theme.MovieCatalogTheme
import com.moviecatalog.feature.catalog.CatalogScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCatalogTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "catalog"
                ) {
                    composable("catalog") {
                        CatalogScreen(navController)
                    }

                    composable("details") {
                        Text("Здесь будет экран деталей фильма")
                    }
                }
            }
        }
    }
}





