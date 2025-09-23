package com.moviecatalog.feature.catalog.domain

import com.moviecatalog.feature.catalog.domain.models.Category
import com.moviecatalog.feature.catalog.domain.models.Movie
import com.moviecatalog.feature.catalog.domain.models.MovieState
import kotlinx.coroutines.flow.MutableStateFlow

class MovieInteractor(
    private val repo: MovieRepository
) {
    val state: MutableStateFlow<MovieState> =
        MutableStateFlow(MovieState(emptyList(), Category.Trending))

    fun getMovies(category: Category): List<Movie> {
        return when (category) {
            Category.Trending -> repo.getTrending()
            Category.Popular -> repo.getPopular()
            Category.TopRated -> repo.getTopRated()
        }
    }

    fun updateCategory(category: Category) {
        state.value = MovieState(getMovies(category), category)
    }

    fun init() {
        updateCategory(Category.Trending)
    }
}