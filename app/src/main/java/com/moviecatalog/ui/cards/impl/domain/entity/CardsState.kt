package com.moviecatalog.ui.cards.impl.domain.entity

import com.moviecatalog.feature.cards.api.entity.Card
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

sealed interface CardsScreenState {
    data object Loading : CardsScreenState
    data class Success(val cardsState: CardsState) : CardsScreenState
    data class Error(val message: String) : CardsScreenState
}

data class CardsState(
    val cards: List<Card>,
)
