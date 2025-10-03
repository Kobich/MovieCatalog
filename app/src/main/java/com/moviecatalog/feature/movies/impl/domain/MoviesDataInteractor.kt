package com.moviecatalog.feature.movies.impl.domain

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

class MoviesDataInteractor(
    private val repo: MovieRepository
) {
    suspend fun getMovies(category: Category): List<Movie> {
        return repo.getMovies(category)
    }

    suspend fun getMovieById(id: Int): Movie? {
        return repo.getMovieById(id)
    }
}