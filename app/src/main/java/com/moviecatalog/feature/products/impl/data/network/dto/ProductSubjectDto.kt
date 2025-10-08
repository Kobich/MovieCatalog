package com.moviecatalog.feature.products.impl.data.network.dto

import com.squareup.moshi.Json

data class ProductSubjectDto(
    @param:Json(name = "subjectID") val subjectId: Int,
    @param:Json(name = "subjectName") val subjectName: String,
    @param:Json(name = "parentID") val parentId: Int,
    @param:Json(name = "parentName") val parentName: String
)
