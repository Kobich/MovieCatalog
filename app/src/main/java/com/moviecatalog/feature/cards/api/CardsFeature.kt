package com.moviecatalog.feature.cards.api

import com.moviecatalog.feature.cards.api.entity.Card

interface CardsFeature {
    suspend fun getCards(): List<Card>
}
