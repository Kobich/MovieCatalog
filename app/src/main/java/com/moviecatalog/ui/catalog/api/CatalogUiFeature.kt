package com.moviecatalog.ui.catalog.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface CatalogUiFeature {
    @Composable
    fun Content(navController: NavHostController)
}