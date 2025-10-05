package com.moviecatalog.ui.catalog.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.moviecatalog.ui.catalog.api.CatalogUiFeature
import com.moviecatalog.ui.catalog.impl.ui.CatalogScreen
import com.moviecatalog.ui.details.impl.ui.DetailsScreen

internal class CatalogUiFeatureImpl: CatalogUiFeature {

    @Composable
    override fun Content(navController: NavHostController) {
        CatalogScreen(navController)
    }
}