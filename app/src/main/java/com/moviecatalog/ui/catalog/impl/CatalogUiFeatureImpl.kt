package com.moviecatalog.ui.catalog.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.moviecatalog.ui.catalog.api.CatalogUiFeature
import com.moviecatalog.ui.catalog.impl.ui.CatalogScreen

internal class CatalogUiFeatureImpl: CatalogUiFeature {

    @Composable
    override fun Content(navController: NavHostController) {
        CatalogScreen(navController)
    }
}