package com.moviecatalog.feature.products.api.entity

data class ProductCharacteristic(
    val charcId: Int,
    val subjectId: Int,
    val subjectName: String,
    val name: String,
    val unitName: String?,
    val required: Boolean,
    val maxCount: Int,
    val popular: Boolean,
    val charcType: Int
)

