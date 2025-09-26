package com.moviecatalog.feature.catalog.domain

import com.moviecatalog.feature.catalog.domain.entity.Movie

interface MovieRepository {
    fun getTrending(): List<Movie>
    fun getPopular(): List<Movie>
    fun getTopRated(): List<Movie>
    fun getMovieById(id: Int): Movie?
}