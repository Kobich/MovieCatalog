package com.moviecatalog.ui.main.impl

import androidx.compose.runtime.Composable
import com.moviecatalog.ui.card.api.CardDetailsUiFeature
import com.moviecatalog.ui.cards.api.CardsUiFeature
import com.moviecatalog.ui.main.api.MainUiFeature
import com.moviecatalog.ui.main.impl.ui.MainScreen

internal class MainUiFeatureImpl(
    private val cardsUiFeature: CardsUiFeature,
    private val cardDetailsUiFeature: CardDetailsUiFeature,
) : MainUiFeature {

    @Composable
    override fun Content() {
        MainScreen(
            cardsUiFeature = cardsUiFeature,
            cardDetailsUiFeature = cardDetailsUiFeature,
        )
    }
}
