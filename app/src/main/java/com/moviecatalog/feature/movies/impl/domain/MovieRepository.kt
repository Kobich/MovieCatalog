package com.moviecatalog.feature.movies.impl.domain

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

interface MovieRepository {
    suspend fun getMovies(category: Category): List<Movie>
    suspend fun getMovieById(id: Int): Movie?
}