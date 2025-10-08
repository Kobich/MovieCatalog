package com.moviecatalog.feature.products.impl.data.network.dto

import com.squareup.moshi.Json

data class ProductCharacteristicsResponse(
    @param:Json(name = "data") val items: List<ProductCharacteristicDto> = emptyList()
)
