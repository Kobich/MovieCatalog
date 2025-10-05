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
            is DetailsScreenState.Loading -> MovieDetailsUiState.Loading
            is DetailsScreenState.Error -> MovieDetailsUiState.Error(message)
            is DetailsScreenState.Success -> MovieDetailsUiState.Content(state = this.movieState)
        }
    }
}
