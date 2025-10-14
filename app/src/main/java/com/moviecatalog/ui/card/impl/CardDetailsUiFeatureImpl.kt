package com.moviecatalog.ui.card.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.moviecatalog.ui.card.api.CardDetailsUiFeature
import com.moviecatalog.ui.card.impl.ui.CardDetailsScreen

internal class CardDetailsUiFeatureImpl : CardDetailsUiFeature {

    @Composable
    override fun Content(navController: NavHostController, imtID: Long) {
        CardDetailsScreen(navController = navController, imtID = imtID)
    }
}
