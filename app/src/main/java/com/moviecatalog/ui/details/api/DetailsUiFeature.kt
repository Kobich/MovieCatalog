package com.moviecatalog.ui.details.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface DetailsUiFeature {
    @Composable
    fun Content(navController: NavHostController, movieId: Int)
}