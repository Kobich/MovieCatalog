package com.moviecatalog.feature.catalog.domain.models

data class MovieState(
    val movies: List<Movie>,
    val selectedCategory: Category,
)
