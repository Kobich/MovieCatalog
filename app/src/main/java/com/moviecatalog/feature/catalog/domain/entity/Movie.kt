package com.moviecatalog.feature.catalog.domain.entity

data class Movie(
    val id: Int,
    val title: String,
    val posterRes: Int,
    val description: String,
    val rating: Double,
    val actors: List<String>
)

