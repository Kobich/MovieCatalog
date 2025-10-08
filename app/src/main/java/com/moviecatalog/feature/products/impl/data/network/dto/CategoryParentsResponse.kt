package com.moviecatalog.feature.products.impl.data.network.dto

import com.squareup.moshi.Json

data class CategoryParentsResponse(
    @param:Json(name = "data") val items: List<CategoryParentDto> = emptyList()
)
