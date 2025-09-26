package com.moviecatalog.feature.movies.impl

import com.moviecatalog.feature.movies.api.MoviesFeature
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.feature.movies.impl.domain.MoviesDataInteractor

class MoviesFeatureImpl(
    private val moviesDataInteractor: MoviesDataInteractor
): MoviesFeature {
    override fun getMovies(category: Category): List<Movie> {
        return moviesDataInteractor.getMovies(category)
    }

    override fun getMovieById(id: Int): Movie? {
        return moviesDataInteractor.getMovieById(id)
    }

}