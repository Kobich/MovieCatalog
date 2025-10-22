package com.wbprofit.ui.card.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface CardDetailsUiFeature {
    @Composable
    fun Content(navController: NavHostController, nmId: Long)
}