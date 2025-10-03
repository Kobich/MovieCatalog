package com.moviecatalog.di

import com.moviecatalog.feature.movies.impl.data.MovieRepositoryImpl
import com.moviecatalog.ui.catalog.domain.CatalogInteractor
import com.moviecatalog.feature.movies.impl.MoviesFeatureImpl
import com.moviecatalog.feature.movies.impl.domain.MovieRepository
import com.moviecatalog.feature.movies.impl.domain.MoviesDataInteractor
import com.moviecatalog.ui.details.domain.DetailsInteractor

object Di {

    private val moviesFeature = MoviesFeatureImpl(
        moviesDataInteractor = getMoviesDataInteractor()
    )

    private val catalogInteractor =  CatalogInteractor(getMoviesFeature())
    fun getСatalogInteractor() = catalogInteractor

    private val detailsInteractor =  DetailsInteractor(getMoviesFeature())
    fun getDetailsInteractor() = detailsInteractor


    // MoviesFeature
    fun getMovieDataRepository(): MovieRepository = MovieRepositoryImpl()
    fun getMoviesDataInteractor()  = MoviesDataInteractor(
        repo = getMovieDataRepository()
    )


    fun getMoviesFeature() = moviesFeature
}