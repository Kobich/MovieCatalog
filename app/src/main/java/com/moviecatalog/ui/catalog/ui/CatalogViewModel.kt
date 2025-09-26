package com.moviecatalog.ui.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.di.Di
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.ui.catalog.ui.entity.MovieUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CatalogViewModel : ViewModel() {

    private val interactor = Di.getMovieInteractor()

    val uiState: StateFlow<MovieUiState> = interactor.state
        .map {
            MovieUiState(
                movies = it.movies,
                selectedCategory = it.selectedCategory
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MovieUiState(),
        )

    init {
        interactor.init()
    }

    fun updateCategory(category: Category) {
        interactor.updateCategory(category)
    }
}