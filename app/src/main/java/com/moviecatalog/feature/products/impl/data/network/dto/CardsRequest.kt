package com.moviecatalog.feature.products.impl.data.network.dto

import com.squareup.moshi.Json

data class CardsRequest(
    @param:Json(name = "settings") val settings: SettingsDto? = null,
)

data class SettingsDto(
    @param:Json(name = "sort") val sort: SortDto = SortDto(),
    @param:Json(name = "filter") val filter: FilterDto = FilterDto(),
    @param:Json(name = "cursor") val cursor: CursorDto? = null,
)

data class SortDto(
    @param:Json(name = "ascending") val ascending: Boolean = false,
)

data class FilterDto(
    @param:Json(name = "textSearch") val textSearch: String = "",
    @param:Json(name = "allowedCategoriesOnly") val allowedCategoriesOnly: Boolean = false,
    @param:Json(name = "tagIDs") val tagIDs: List<Int>? = null,
    @param:Json(name = "objectIDs") val objectIDs: List<Int>? = null,
    @param:Json(name = "brands") val brands: List<String>? = null,
    @param:Json(name = "imtID") val imtID: Long? = null,
    @param:Json(name = "withPhoto") val withPhoto: Int = -1,
)

data class CursorDto(
    @param:Json(name = "cursor") val cursor: String,
    @param:Json(name = "nmID") val nmID: Long,
    @param:Json(name = "limit") val limit: Int,
)
