package com.moviecatalog.feature.cards.impl.domain

import com.moviecatalog.feature.cards.api.entity.Card

internal interface CardsRepository {
    suspend fun getCards(): CardsResult
}

internal data class CardsResult(
    val cards: List<Card>,
    val cursor: CursorResult
)

internal data class CursorResult(
    val nmID: Long,
    val total: Long,
)