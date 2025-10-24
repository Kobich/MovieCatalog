package com.wbprofit.ui.card.impl.domain

import com.wbprofit.feature.cards.api.entity.CardDetail

internal sealed interface CardDetailsScreenState {
    data object Loading : CardDetailsScreenState

    data class Success(val card: CardDetail) : CardDetailsScreenState

    data class Error(val message: String) : CardDetailsScreenState
}
