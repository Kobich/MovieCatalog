package com.moviecatalog.ui.catalog.domain.entity

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

data class MovieState(
    val movies: List<Movie>,
    val selectedCategory: Category,
)
