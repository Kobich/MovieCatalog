package com.moviecatalog.ui.catalog.domain.entity

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie


sealed interface CatalogScreenState {
    data object Loading : CatalogScreenState
    data class Success(val movieState: MovieState) : CatalogScreenState
    data class Error(val message: String) : CatalogScreenState
}

data class MovieState(
    val movies: List<Movie>,
    val selectedCategory: Category,
)
