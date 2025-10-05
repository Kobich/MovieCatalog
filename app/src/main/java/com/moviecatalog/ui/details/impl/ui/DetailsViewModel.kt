package com.moviecatalog.ui.details.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.ui.details.impl.domain.DetailsInteractor
import com.moviecatalog.ui.details.impl.domain.DetailsScreenState
import com.moviecatalog.ui.details.impl.ui.entity.MovieDetailsUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class DetailsViewModel(
    movieId: Int,
    interactor: DetailsInteractor,
) : ViewModel() {

    val uiState: StateFlow<MovieDetailsUiState> = interactor.state
        .map { it.map() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MovieDetailsUiState.Loading,
        )

    init {
        interactor.setMovieId(movieId)
    }

    private fun DetailsScreenState.map(): MovieDetailsUiState {
        return when (this) {
            is DetailsScreenState.Loading -> com.moviecatalog.ui.details.impl.ui.entity.MovieDetailsUiState.Loading
            is DetailsScreenState.Error -> com.moviecatalog.ui.details.impl.ui.entity.MovieDetailsUiState.Error(message)
            is DetailsScreenState.Success -> com.moviecatalog.ui.details.impl.ui.entity.MovieDetailsUiState.Content(state = this.movieState)
        }
    }
}
