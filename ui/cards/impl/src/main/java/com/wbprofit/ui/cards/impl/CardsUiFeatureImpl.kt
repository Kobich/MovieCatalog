package com.wbprofit.ui.cards.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wbprofit.ui.cards.api.CardsUiFeature
import com.wbprofit.ui.cards.impl.ui.CardsScreen

internal class CardsUiFeatureImpl : CardsUiFeature {
    @Composable
    override fun Content(navController: NavHostController) {
        CardsScreen(navController)
    }
}
