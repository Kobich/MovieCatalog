package com.example.feature.cards.impl.domain

import com.example.feature.cards.api.entity.Card
import com.example.feature.cards.api.entity.CardDetail

internal interface CardsRepository {
    suspend fun getCards(): CardsResult
    suspend fun getCard(nmId: Long): CardDetail?
}

internal data class CardsResult(
    val cards: List<Card>,
    val cursor: CursorResult
)

internal data class CursorResult(
    val nmID: Long,
    val total: Long,
)
