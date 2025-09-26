package com.moviecatalog.feature.movies.api

import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.feature.movies.api.entity.Category

interface MoviesFeature {
    fun getMovies(category: Category): List<Movie>
    fun getMovieById(id: Int): Movie?
}