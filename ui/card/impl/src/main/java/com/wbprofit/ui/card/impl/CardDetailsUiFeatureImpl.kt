package com.wbprofit.ui.card.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wbprofit.ui.card.api.CardDetailsUiFeature
import com.wbprofit.ui.card.impl.ui.CardDetailsScreen

internal class CardDetailsUiFeatureImpl : CardDetailsUiFeature {
    @Composable
    override fun Content(navController: NavHostController, nmId: Long) {
        CardDetailsScreen(navController = navController, nmId = nmId)
    }
}
