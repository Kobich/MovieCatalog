package com.moviecatalog.ui.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.ui.details.domain.DetailsInteractor
import com.moviecatalog.ui.details.domain.DetailsScreenState
import com.moviecatalog.ui.details.ui.entity.MovieDetailsUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    private val interactor: DetailsInteractor,
) : ViewModel() {

    val uiState: StateFlow<MovieDetailsUiState> = interactor.state
        .map { it.map() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MovieDetailsUiState.Loading,
        )

    private fun DetailsScreenState.map(): MovieDetailsUiState {
        return when (this) {
            is DetailsScreenState.Loading -> MovieDetailsUiState.Loading
            is DetailsScreenState.Error -> MovieDetailsUiState.Error(message)
            is DetailsScreenState.Success -> MovieDetailsUiState.Content(state = this.movieState)
        }
    }

    fun setMovieId(id: Int) {
        interactor.setMovieId(id)
    }
}
