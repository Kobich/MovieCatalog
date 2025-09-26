package com.moviecatalog.di

import com.moviecatalog.feature.movies.impl.data.MovieRepositoryImpl
import com.moviecatalog.ui.catalog.domain.MovieInteractor
import com.moviecatalog.feature.movies.impl.MoviesFeatureImpl
import com.moviecatalog.feature.movies.impl.domain.MovieRepository
import com.moviecatalog.feature.movies.impl.domain.MoviesDataInteractor

object Di {

    private val moviesFeature = MoviesFeatureImpl(
        moviesDataInteractor = getMoviesDataInteractor()
    )

    fun getMovieInteractor(): MovieInteractor = MovieInteractor(getMoviesFeature())

    // MoviesFeature
    fun getMovieDataRepository(): MovieRepository = MovieRepositoryImpl()
    fun getMoviesDataInteractor()  = MoviesDataInteractor(
        repo = getMovieDataRepository()
    )


    fun getMoviesFeature() = moviesFeature
}