package com.wbprofit.ui.cards.impl.domain.entity

import com.wbprofit.feature.cards.api.entity.Card

sealed interface CardsScreenState {
    data object Loading : CardsScreenState
    data class Success(val cardsState: CardsState) : CardsScreenState
    data class Error(val message: String) : CardsScreenState
}

data class CardsState(
    val cards: List<Card>,
)
