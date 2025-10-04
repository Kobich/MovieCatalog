package com.moviecatalog.ui.details.impl.domain

import com.moviecatalog.feature.movies.api.entity.Movie

internal sealed interface DetailsScreenState {
    data object Loading : DetailsScreenState
    data class Success(val movieState: Movie) : DetailsScreenState
    data class Error(val message: String) : DetailsScreenState
}