package com.moviecatalog.feature.cards.impl

import com.moviecatalog.feature.cards.api.CardsFeature
import com.moviecatalog.feature.cards.api.entity.Card
import com.moviecatalog.feature.cards.impl.domain.CardsInteractor

internal class CardsFeatureImpl(
    private val cardsInteractor: CardsInteractor
) : CardsFeature {

    override suspend fun getCards(): List<Card> {
        return cardsInteractor.getCards()
    }

}
