package com.example.feature.cards.impl

import com.example.feature.cards.impl.domain.CardsInteractor
import com.example.feature.cards.api.CardsFeature
import com.example.feature.cards.api.entity.Card
import com.example.feature.cards.api.entity.CardDetail


internal class CardsFeatureImpl(
    private val cardsInteractor: CardsInteractor
) : CardsFeature {

    override suspend fun getCards(): List<Card> {
        return cardsInteractor.getCards()
    }

    override suspend fun getCard(nmId: Long): CardDetail? {
        return cardsInteractor.getCard(nmId)
    }

}
