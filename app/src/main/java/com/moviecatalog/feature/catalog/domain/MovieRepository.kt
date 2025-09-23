package com.moviecatalog.feature.catalog.domain

import com.moviecatalog.feature.catalog.domain.models.Movie

interface MovieRepository {
    fun getTrending(): List<Movie>
    fun getPopular(): List<Movie>
    fun getTopRated(): List<Movie>
}