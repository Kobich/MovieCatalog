package com.moviecatalog.ui.details.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.moviecatalog.ui.details.api.DetailsUiFeature
import com.moviecatalog.ui.details.impl.ui.DetailsScreen

internal class DetailsUiFeatureImpl : DetailsUiFeature {

    @Composable
    override fun Content(navController: NavHostController, movieId: Int) {
        DetailsScreen(navController, movieId)
    }
}