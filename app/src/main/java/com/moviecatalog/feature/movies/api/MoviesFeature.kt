package com.moviecatalog.feature.movies.api

import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.feature.movies.api.entity.Category

interface MoviesFeature {
    suspend fun getMovies(category: Category): List<Movie>
    suspend fun getMovieById(id: Int): Movie?
}