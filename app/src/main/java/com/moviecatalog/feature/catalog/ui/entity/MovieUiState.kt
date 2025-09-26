package com.moviecatalog.feature.catalog.ui.entity

import com.moviecatalog.feature.catalog.domain.entity.Category
import com.moviecatalog.feature.catalog.domain.entity.Movie

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val selectedCategory: Category = Category.Trending,
)