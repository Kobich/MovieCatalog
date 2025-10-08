package com.moviecatalog.feature.products.api.entity

data class ProductSubject(
    val subjectID: Int,
    val parentID: Int,
    val subjectName: String,
    val parentName: String,
)