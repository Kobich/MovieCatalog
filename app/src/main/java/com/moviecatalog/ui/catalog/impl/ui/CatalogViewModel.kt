package com.moviecatalog.ui.catalog.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.ui.catalog.impl.domain.CatalogInteractor
import com.moviecatalog.ui.catalog.impl.domain.entity.CatalogScreenState
import com.moviecatalog.ui.catalog.impl.ui.entity.CatalogScreenUiState
import com.moviecatalog.ui.catalog.impl.ui.entity.MovieUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CatalogViewModel(
    private val interactor: CatalogInteractor,
) : ViewModel() {

    val uiState: StateFlow<CatalogScreenUiState> = interactor.state
        .map { it.map() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = CatalogScreenUiState.Loading,
        )

    init {
        interactor.init()
    }

    fun updateCategory(category: Category) {
        interactor.updateCategory(category)
    }

    private fun CatalogScreenState.map(): CatalogScreenUiState {
        return when (this) {
            is CatalogScreenState.Loading -> CatalogScreenUiState.Loading
            is CatalogScreenState.Error -> CatalogScreenUiState.Error(message)
            is CatalogScreenState.Success -> CatalogScreenUiState.Content(
                MovieUiState(
                    movies = this.movieState.movies,
                    selectedCategory = this.movieState.selectedCategory
                )
            )
        }
    }
}