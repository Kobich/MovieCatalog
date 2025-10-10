package com.moviecatalog.feature.products.impl.data.network.dto

import com.squareup.moshi.Json

data class CardsResponseDto(
    @param:Json(name = "cards") val cards: List<CardDto>,
)

data class CardDto(
    @param:Json(name = "nmID") val nmID: Long,
    @param:Json(name = "imtID") val imtID: Long,
    @param:Json(name = "subjectName") val subjectName: String,
)