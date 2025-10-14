package com.moviecatalog.feature.cards.api

import com.moviecatalog.feature.cards.api.entity.Card
import com.moviecatalog.feature.cards.api.entity.CardDetail

interface CardsFeature {
    suspend fun getCards(): List<Card>
    suspend fun getCard(nmId: Long): CardDetail?
}
