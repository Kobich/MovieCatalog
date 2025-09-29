package com.moviecatalog.ui.details.ui.entity

import com.moviecatalog.feature.movies.api.entity.Movie




sealed class MovieDetailsUiState {
    data object Loading : MovieDetailsUiState()
    data class Content(val state: Movie) : MovieDetailsUiState()
    data class Error(val message: String) : MovieDetailsUiState()
}