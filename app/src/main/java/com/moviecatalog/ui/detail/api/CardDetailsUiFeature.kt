package com.moviecatalog.ui.detail.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface CardDetailsUiFeature {
    @Composable
    fun Content(navController: NavHostController, cardId: Long)
}
