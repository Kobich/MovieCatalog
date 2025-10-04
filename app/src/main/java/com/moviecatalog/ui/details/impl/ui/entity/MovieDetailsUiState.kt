package com.moviecatalog.ui.details.impl.ui.entity

import com.moviecatalog.feature.movies.api.entity.Movie

internal sealed class MovieDetailsUiState {
    data object Loading : MovieDetailsUiState()
    data class Content(val state: Movie) : MovieDetailsUiState()
    data class Error(val message: String) : MovieDetailsUiState()
}