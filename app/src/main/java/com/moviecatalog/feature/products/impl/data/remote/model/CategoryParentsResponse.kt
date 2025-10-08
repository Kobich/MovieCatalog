package com.moviecatalog.feature.products.impl.data.remote.model

import com.squareup.moshi.Json

data class CategoryParentsResponse(
    @param:Json(name = "data") val items: List<CategoryParentDto> = emptyList()
)
