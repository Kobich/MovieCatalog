package com.moviecatalog.feature.catalog.ui.entity

import com.moviecatalog.feature.catalog.domain.models.Category
import com.moviecatalog.feature.catalog.domain.models.Movie

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val selectedCategory: Category = Category.Trending,
)