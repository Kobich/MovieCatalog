package com.moviecatalog.feature.cards.impl.data.network

import com.moviecatalog.feature.cards.impl.data.network.dto.CardsRequest
import com.moviecatalog.feature.cards.impl.data.network.dto.CardsResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface CardsApi {

    @POST("/content/v2/get/cards/list")
    suspend fun getCards(
        @Body body: CardsRequest
    ): CardsResponseDto
}
