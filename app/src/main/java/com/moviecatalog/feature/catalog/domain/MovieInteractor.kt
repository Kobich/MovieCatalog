package com.moviecatalog.feature.catalog.domain

import com.moviecatalog.feature.catalog.domain.entity.Category
import com.moviecatalog.feature.catalog.domain.entity.Movie
import com.moviecatalog.feature.catalog.domain.entity.MovieState
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
    fun getMovieById(id: Int): Movie? {
        return Category.entries
            .asSequence()
            .map { getMovies(it) }
            .flatten()
            .find { it.id == id }
    }


    fun init() {
        updateCategory(Category.Trending)
    }
}