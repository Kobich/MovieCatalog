package com.moviecatalog.feature.catalog.domain.entity

data class MovieState(
    val movies: List<Movie>,
    val selectedCategory: Category,
)
