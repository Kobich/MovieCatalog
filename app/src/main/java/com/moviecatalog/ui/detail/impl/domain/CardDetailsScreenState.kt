package com.moviecatalog.ui.detail.impl.domain

import com.moviecatalog.feature.cards.api.entity.CardDetail

internal sealed interface CardDetailsScreenState {
    data object Loading : CardDetailsScreenState
    data class Success(val card: CardDetail) : CardDetailsScreenState
    data class Error(val message: String) : CardDetailsScreenState
}
