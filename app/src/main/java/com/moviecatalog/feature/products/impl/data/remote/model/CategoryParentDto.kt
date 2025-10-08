package com.moviecatalog.feature.products.impl.data.remote.model

import com.squareup.moshi.Json

data class CategoryParentDto(
    @param:Json(name = "id") val id: Int,
    @param:Json(name = "name") val name: String,
    @param:Json(name = "isVisible") val isVisible: Boolean
)
