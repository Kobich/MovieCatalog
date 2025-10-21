package com.example.feature.cards.impl.domain

import com.example.feature.cards.api.entity.Card
import com.example.feature.cards.api.entity.CardDetail

internal class CardsInteractor(
    private val repo: CardsRepository
) {
    suspend fun getCards(): List<Card> {
        return repo.getCards().cards
    }

    suspend fun getCard(nmId: Long): CardDetail? {
        return repo.getCard(nmId)
    }
}
