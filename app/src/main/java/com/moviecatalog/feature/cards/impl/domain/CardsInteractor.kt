package com.moviecatalog.feature.cards.impl.domain

import com.moviecatalog.feature.cards.api.entity.Card
import com.moviecatalog.feature.cards.api.entity.CardDetail

internal class CardsInteractor(
    private val repo: CardsRepository
) {
    suspend fun getCards(): List<Card> {
        return repo.getCards().cards
    }

    suspend fun getCard(imtID: Long): CardDetail? {
        return repo.getCard(imtID)
    }
}
