package com.moviecatalog.ui.catalog.impl.ui.entity

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie


sealed class CatalogScreenUiState {
    data object Loading : CatalogScreenUiState()
    data class Content(val state: MovieUiState) : CatalogScreenUiState()
    data class Error(val message: String) : CatalogScreenUiState()
}

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val selectedCategory: Category = Category.Trending,
)