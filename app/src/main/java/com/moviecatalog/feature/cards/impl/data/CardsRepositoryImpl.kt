package com.moviecatalog.feature.cards.impl.data

import com.moviecatalog.feature.cards.impl.data.network.CardsApi
import com.moviecatalog.feature.cards.impl.data.network.dto.CardsRequest
import com.moviecatalog.feature.cards.impl.data.network.mapper.CardsDtoMapper
import com.moviecatalog.feature.cards.impl.domain.CardsRepository
import com.moviecatalog.feature.cards.impl.domain.CardsResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CardsRepositoryImpl(
    private val api: CardsApi,
    private val mapper: CardsDtoMapper,
) : CardsRepository {

    override suspend fun getCards(): CardsResult {
        return withContext(Dispatchers.IO) {
            api.getCards(CardsRequest())
                .let { mapper.map(it) }
                .also {
                    println("getCards: $it")
                }
        }
    }
}
