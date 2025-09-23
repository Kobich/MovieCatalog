package com.moviecatalog.di

import com.moviecatalog.feature.catalog.data.FakeMovieRepositoryImpl
import com.moviecatalog.feature.catalog.domain.MovieInteractor
import com.moviecatalog.feature.catalog.domain.MovieRepository

object Di {
    val movieRepository = FakeMovieRepositoryImpl()
    fun getMovieRepository(): MovieRepository = movieRepository
    fun getMovieInteractor(): MovieInteractor = MovieInteractor(getMovieRepository())
}