package com.moviecatalog.feature.details.ui.entity

import com.moviecatalog.feature.catalog.domain.entity.Category
import com.moviecatalog.feature.catalog.domain.entity.Movie


data class MovieDetailsUiState(
    val loading: List<Movie> = emptyList(),
    val content: String = "",
    val error: String = "",
)