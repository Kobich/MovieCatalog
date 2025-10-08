package com.moviecatalog.feature.products.impl.data.network.dto

import com.squareup.moshi.Json

data class ProductSubjectsResponse(
    @param:Json(name = "data") val items: List<ProductSubjectDto> = emptyList()
)
