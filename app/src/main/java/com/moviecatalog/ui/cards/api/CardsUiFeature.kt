package com.moviecatalog.ui.cards.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface CardsUiFeature {
    @Composable
    fun Content(navController: NavHostController)
}