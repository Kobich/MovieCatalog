package com.moviecatalog.ui.detail.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.moviecatalog.ui.detail.api.CardDetailsUiFeature
import com.moviecatalog.ui.detail.impl.ui.CardDetailsScreen

internal class CardDetailsUiFeatureImpl : CardDetailsUiFeature {

    @Composable
    override fun Content(navController: NavHostController, imtID: Long) {
        CardDetailsScreen(navController = navController, imtID = imtID)
    }
}
