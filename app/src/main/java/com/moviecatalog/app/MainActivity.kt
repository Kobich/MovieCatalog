package com.moviecatalog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.moviecatalog.base.ui.theme.MovieCatalogTheme
import com.moviecatalog.ui.main.api.MainUiFeature
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val mainUiFeature by inject<MainUiFeature>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCatalogTheme {
                mainUiFeature.Content()
            }
        }
    }
}
