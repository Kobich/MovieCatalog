package com.example.feature.cards.api

import com.example.feature.cards.api.entity.Card
import com.example.feature.cards.api.entity.CardDetail

interface CardsFeature {
    suspend fun getCards(): List<Card>
    suspend fun getCard(nmId: Long): CardDetail?
}
