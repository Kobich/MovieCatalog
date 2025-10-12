package com.moviecatalog.feature.cards.impl.domain

import com.moviecatalog.feature.cards.api.entity.Card

internal class CardsInteractor(
    private val repo: CardsRepository
) {
    suspend fun getCards(): List<Card> {
        return repo.getCards().cards
    }
}
