package com.moviecatalog.feature.cards.impl.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardsResponseDto(
    @param:Json(name = "cards") val cards: List<CardDto>,
    @param:Json(name = "cursor") val cursor: CursorResultDto,
)

@JsonClass(generateAdapter = true)
data class CardDto(
    @param:Json(name = "nmID") val nmID: Long,
    @param:Json(name = "imtID") val imtID: Long,
    @param:Json(name = "subjectName") val subjectName: String,
)

@JsonClass(generateAdapter = true)
data class CursorResultDto(
    @param:Json(name = "nmID") val nmID: Long,
    @param:Json(name = "total") val total: Long,
)
