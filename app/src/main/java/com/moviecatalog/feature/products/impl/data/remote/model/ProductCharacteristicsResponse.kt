package com.moviecatalog.feature.products.impl.data.remote.model

import com.squareup.moshi.Json

data class ProductCharacteristicsResponse(
    @param:Json(name = "data") val items: List<ProductCharacteristicDto> = emptyList()
)
