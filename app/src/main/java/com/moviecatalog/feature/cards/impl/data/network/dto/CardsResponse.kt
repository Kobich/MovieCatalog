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
    @param:Json(name = "nmUUID") val nmUUID: String? = null,
    @param:Json(name = "subjectID") val subjectID: Long? = null,
    @param:Json(name = "subjectName") val subjectName: String? = null,
    @param:Json(name = "vendorCode") val vendorCode: String? = null,
    @param:Json(name = "brand") val brand: String? = null,
    @param:Json(name = "title") val title: String? = null,
    @param:Json(name = "description") val description: String? = null,
    @param:Json(name = "needKiz") val needKiz: Boolean? = null,
    @param:Json(name = "photos") val photos: List<PhotoDto> = emptyList(),
    @param:Json(name = "video") val video: String? = null,
    @param:Json(name = "wholesale") val wholesale: WholesaleDto? = null,
    @param:Json(name = "dimensions") val dimensions: DimensionsDto? = null,
    @param:Json(name = "characteristics") val characteristics: List<CharacteristicDto> = emptyList(),
    @param:Json(name = "sizes") val sizes: List<SizeDto> = emptyList(),
    @param:Json(name = "tags") val tags: List<TagDto> = emptyList(),
    @param:Json(name = "createdAt") val createdAt: String? = null,
    @param:Json(name = "updatedAt") val updatedAt: String? = null,
)

@JsonClass(generateAdapter = true)
data class PhotoDto(
    @param:Json(name = "big") val big: String? = null,
    @param:Json(name = "c246x328") val c246x328: String? = null,
    @param:Json(name = "c516x688") val c516x688: String? = null,
    @param:Json(name = "square") val square: String? = null,
    @param:Json(name = "tm") val tm: String? = null,
)

@JsonClass(generateAdapter = true)
data class WholesaleDto(
    @param:Json(name = "enabled") val enabled: Boolean? = null,
    @param:Json(name = "quantum") val quantum: Int? = null,
)

@JsonClass(generateAdapter = true)
data class DimensionsDto(
    @param:Json(name = "length") val length: Int? = null,
    @param:Json(name = "width") val width: Int? = null,
    @param:Json(name = "height") val height: Int? = null,
    @param:Json(name = "weightBrutto") val weightBrutto: Double? = null,
    @param:Json(name = "isValid") val isValid: Boolean? = null,
)

@JsonClass(generateAdapter = true)
data class CharacteristicDto(
    @param:Json(name = "id") val id: Long? = null,
    @param:Json(name = "name") val name: String? = null,
    @param:Json(name = "value") internal val _valueRaw: Any? = null,
) {
    val value: List<String> = when (_valueRaw) {
        is String -> listOf(_valueRaw)
        is List<*> -> _valueRaw.filterIsInstance<String>()
        else -> emptyList()
    }
}
@JsonClass(generateAdapter = true)
data class SizeDto(
    @param:Json(name = "chrtID") val chrtID: Long? = null,
    @param:Json(name = "techSize") val techSize: String? = null,
    @param:Json(name = "skus") val skus: List<String> = emptyList(),
)

@JsonClass(generateAdapter = true)
data class TagDto(
    @param:Json(name = "id") val id: Long? = null,
    @param:Json(name = "name") val name: String? = null,
    @param:Json(name = "color") val color: String? = null,
)

@JsonClass(generateAdapter = true)
data class CursorResultDto(
    @param:Json(name = "updatedAt") val updatedAt: String? = null,
    @param:Json(name = "nmID") val nmID: Long,
    @param:Json(name = "total") val total: Long,
)
