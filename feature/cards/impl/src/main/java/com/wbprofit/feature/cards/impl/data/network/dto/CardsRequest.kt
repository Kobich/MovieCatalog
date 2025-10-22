package com.wbprofit.feature.cards.impl.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardsRequest(
    @param:Json(name = "settings") val settings: SettingsDto = SettingsDto(),
)

@JsonClass(generateAdapter = true)
data class SettingsDto(
    @param:Json(name = "sort") val sort: SortDto = SortDto(),
    @param:Json(name = "filter") val filter: FilterDto = FilterDto(),
    @param:Json(name = "cursor") val cursor: CursorDto = CursorDto(),
)

@JsonClass(generateAdapter = true)
data class SortDto(
    @param:Json(name = "ascending") val ascending: Boolean = false,
)

@JsonClass(generateAdapter = true)
data class FilterDto(
    @param:Json(name = "textSearch") val textSearch: String? = null,
    @param:Json(name = "allowedCategoriesOnly") val allowedCategoriesOnly: Boolean = false,
    @param:Json(name = "tagIDs") val tagIDs: List<Int>? = null,
    @param:Json(name = "objectIDs") val objectIDs: List<Int>? = null,
    @param:Json(name = "brands") val brands: List<String>? = null,
    @param:Json(name = "imtID") val imtID: Long? = null,
    @param:Json(name = "withPhoto") val withPhoto: Int = -1,
)

@JsonClass(generateAdapter = true)
data class CursorDto(
    @param:Json(name = "updatedAt") val updatedAt: String? = null,
    @param:Json(name = "nmID") val nmID: Long? = null,
    @param:Json(name = "limit") val limit: Int = 100,
)
