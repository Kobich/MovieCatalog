package com.moviecatalog.ui.details.domain

import com.moviecatalog.ui.details.ui.entity.MovieDetailsUiState
import kotlinx.coroutines.flow.StateFlow

interface DetailsInteractor {
    val state: StateFlow<MovieDetailsUiState>
    fun init(movieId: Int)
    fun refresh()
}