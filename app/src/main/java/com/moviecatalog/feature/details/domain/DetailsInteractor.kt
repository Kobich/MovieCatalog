package com.moviecatalog.feature.details.domain

import com.moviecatalog.feature.details.ui.entity.MovieDetailsUiState
import kotlinx.coroutines.flow.StateFlow

interface DetailsInteractor {
    val state: StateFlow<MovieDetailsUiState>
    fun init(movieId: Int)
    fun refresh()
}