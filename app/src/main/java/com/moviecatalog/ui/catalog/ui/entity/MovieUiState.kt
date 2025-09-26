package com.moviecatalog.ui.catalog.ui.entity

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val selectedCategory: Category = Category.Trending,
)