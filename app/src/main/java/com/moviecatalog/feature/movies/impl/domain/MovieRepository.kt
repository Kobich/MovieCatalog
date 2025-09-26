package com.moviecatalog.feature.movies.impl.domain

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

interface MovieRepository {
    fun getMovies(category: Category): List<Movie>
    fun getMovieById(id: Int): Movie?
}