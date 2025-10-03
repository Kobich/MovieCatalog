package com.moviecatalog.feature.movies.api.entity

data class Movie(
    val id: Int,
    val title: String,
    val posterRes: Int,
    val description: String,
    val rating: Double,
    val actors: List<String>,
    val category: Category,
)

