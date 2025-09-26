package com.moviecatalog.feature.movies.impl

import com.moviecatalog.feature.movies.api.MoviesFeature
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.feature.movies.impl.domain.MoviesDataInteractor

class MoviesFeatureImpl(
    private val moviesDataInteractor: MoviesDataInteractor
): MoviesFeature {
    override suspend fun getMovies(category: Category): List<Movie> {
        return moviesDataInteractor.getMovies(category)
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return moviesDataInteractor.getMovieById(id)
    }

}