package com.moviecatalog.feature.movies.impl.di

import com.moviecatalog.feature.movies.api.MoviesFeature
import com.moviecatalog.feature.movies.impl.MoviesFeatureImpl
import com.moviecatalog.feature.movies.impl.data.MovieRepositoryImpl
import com.moviecatalog.feature.movies.impl.domain.MovieRepository
import com.moviecatalog.feature.movies.impl.domain.MoviesDataInteractor
import org.koin.dsl.module

val moviesFeatureModule = module {
    single<MovieRepository> { MovieRepositoryImpl() }
    single { MoviesDataInteractor(repo = get()) }
    single<MoviesFeature> { MoviesFeatureImpl(moviesDataInteractor = get()) }
}
