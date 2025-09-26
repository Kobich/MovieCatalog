package com.moviecatalog.ui.details.domain

import com.moviecatalog.feature.movies.api.entity.Movie

sealed interface DetailsScreenState {
    data object Loading : DetailsScreenState
    data class Success(val movieState: Movie) : DetailsScreenState
    data class Error(val message: String) : DetailsScreenState
}