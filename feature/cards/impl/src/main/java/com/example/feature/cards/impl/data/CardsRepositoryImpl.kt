package com.example.feature.cards.impl.data

import com.example.feature.cards.api.entity.CardDetail
import com.example.feature.cards.impl.data.network.CardsApi
import com.example.feature.cards.impl.data.network.dto.CardsRequest
import com.example.feature.cards.impl.data.network.dto.CursorDto
import com.example.feature.cards.impl.data.network.dto.FilterDto
import com.example.feature.cards.impl.data.network.dto.SettingsDto
import com.example.feature.cards.impl.data.network.mapper.CardsDtoMapper
import com.example.feature.cards.impl.domain.CardsRepository
import com.example.feature.cards.impl.domain.CardsResult
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

    override suspend fun getCard(nmId: Long): CardDetail? {
        return withContext(Dispatchers.IO) {
            val request = CardsRequest(
                settings = SettingsDto(
                    filter = FilterDto(textSearch = nmId.toString()),
                    cursor = CursorDto(limit = 1)
                )
            )

            api.getCards(request)
                .cards
                .firstOrNull()
                ?.let { mapper.mapDetail(it) }
        }
    }
}
