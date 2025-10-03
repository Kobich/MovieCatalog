package com.moviecatalog.ui.catalog.domain

import com.moviecatalog.feature.movies.api.MoviesFeature
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.ui.catalog.domain.entity.CatalogScreenState
import com.moviecatalog.ui.catalog.domain.entity.MovieState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CatalogInteractor(
    private val moviesFeature: MoviesFeature,
) {

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    val state: MutableStateFlow<CatalogScreenState> =
        MutableStateFlow(CatalogScreenState.Loading)

    fun init() {
        updateCategory(Category.Trending)
    }

    fun updateCategory(category: Category) {
        job?.cancel()
        job = coroutineScope.launch {
            state.value = CatalogScreenState.Loading
            try {
                val movies = moviesFeature.getMovies(category)
                state.value = CatalogScreenState.Success(
                    MovieState(
                        movies = movies,
                        selectedCategory = category
                    )
                )
            } catch (e: Exception) {
                state.value = CatalogScreenState.Error(e.message ?: "Unknown error")
            }
        }
    }




}