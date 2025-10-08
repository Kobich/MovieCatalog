package com.moviecatalog.feature.products.impl.data.network.dto

import com.squareup.moshi.Json

data class ProductCharacteristicDto(
    @param:Json(name = "charcID") val characteristicId: Int,
    @param:Json(name = "subjectID") val subjectId: Int,
    @param:Json(name = "subjectName") val subjectName: String,
    @param:Json(name = "parentName") val parentName: String,
    @param:Json(name = "name") val name: String,
    @param:Json(name = "unitName") val unitName: String?,
    @param:Json(name = "required") val isRequired: Boolean,
    @param:Json(name = "maxCount") val maxCount: Int,
    @param:Json(name = "popular") val isPopular: Boolean,
    @param:Json(name = "charcType") val type: Int
)
