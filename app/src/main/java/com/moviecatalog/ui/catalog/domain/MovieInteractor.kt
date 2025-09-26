package com.moviecatalog.ui.catalog.domain

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.ui.catalog.domain.entity.MovieState
import com.moviecatalog.feature.movies.api.MoviesFeature
import kotlinx.coroutines.flow.MutableStateFlow

class MovieInteractor(
    private val moviesFeature: MoviesFeature,
) {
    val state: MutableStateFlow<MovieState> =
        MutableStateFlow(MovieState(emptyList(), Category.Trending))

    fun getMovies(category: Category): List<Movie> {
        return moviesFeature.getMovies(category)
    }

    fun updateCategory(category: Category) {
        state.value = MovieState(getMovies(category), category)
    }

    fun getMovieById(id: Int): Movie? {
        return moviesFeature.getMovieById(id)
    }

    fun init() {
        updateCategory(Category.Trending)
    }
}