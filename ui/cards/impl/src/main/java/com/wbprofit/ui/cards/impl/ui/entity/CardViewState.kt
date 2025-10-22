package com.wbprofit.ui.cards.impl.ui.entity


sealed class CardsScreenViewState {
    data object Loading : CardsScreenViewState()
    data class Content(val state: CardsViewState) : CardsScreenViewState()
    data class Error(val message: String) : CardsScreenViewState()
}

data class CardsViewState(
    val cards: List<CardViewState> = emptyList(),
)

data class CardViewState(
    val nmId: Long,
    val imtID: Long,
    val title: String,
    val imageUrl: String?,
)


