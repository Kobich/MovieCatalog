package com.moviecatalog.ui.details.ui.entity

import com.moviecatalog.feature.movies.api.entity.Movie


data class MovieDetailsUiState(
    val loading: List<Movie> = emptyList(),
    val content: String = "",
    val error: String = "",
)