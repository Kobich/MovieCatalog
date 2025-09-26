package com.moviecatalog.ui.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.di.Di
import com.moviecatalog.feature.movies.api.entity.Movie
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class DetailsViewModel : ViewModel() {
    private val interactor = Di.getDetailsInteractor()

//    val movie: StateFlow<Movie?> = selectedMovieId
//        .map { id -> id?.let { interactor.getMovieById(it) } }
//        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun setMovieId(id: Int) {
        interactor.setMovieId(id)
    }
}
