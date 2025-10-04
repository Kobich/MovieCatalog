package com.moviecatalog.ui.details.impl.domain

import com.moviecatalog.feature.movies.api.MoviesFeature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class DetailsInteractor(
    private val moviesFeature: MoviesFeature,
) {
    val state: MutableStateFlow<DetailsScreenState> =
        MutableStateFlow(DetailsScreenState.Loading)

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    fun setMovieId(id: Int) {
        job?.cancel()
        job = coroutineScope.launch {
            state.value = DetailsScreenState.Loading
            try {
                val movie = moviesFeature.getMovieById(id)
                if (movie != null) {
                    state.value = DetailsScreenState.Success(movie)
                } else {
                    state.value = DetailsScreenState.Error("Movie not found")
                }
            } catch (e: Exception) {
                state.value = DetailsScreenState.Error(e.message ?: "Unknown error")
            }
        }
    }
}