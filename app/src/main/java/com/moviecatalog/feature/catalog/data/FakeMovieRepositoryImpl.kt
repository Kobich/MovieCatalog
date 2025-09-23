package com.moviecatalog.feature.catalog.data

import com.moviecatalog.R
import com.moviecatalog.feature.catalog.domain.MovieRepository
import com.moviecatalog.feature.catalog.domain.models.Movie

class FakeMovieRepositoryImpl : MovieRepository {

    override fun getTrending(): List<Movie> = listOf(
        Movie(1, "Fury", R.drawable.poster_fury),
        Movie(2, "Leon", R.drawable.poster_lion),
        Movie(3, "Akira", R.drawable.poster_akira),
    )

    override fun getPopular(): List<Movie> = listOf(
        Movie(4, "Legend", R.drawable.poster_legend),
        Movie(5, "Bladerunner 2049", R.drawable.poster_bladerunner2049),
    )

    override fun getTopRated(): List<Movie> = listOf(
        Movie(6, "Lord of the rings", R.drawable.poster_lordofthering),
        Movie(7, "Mad Max", R.drawable.poster_madmax),
        Movie(8, "Pilp fiction", R.drawable.poster_pilpfiction),
        Movie(9, "Spider man", R.drawable.poster_spiderman),
        Movie(10, "Star wars", R.drawable.poster_starwars),
        Movie(11, "The godfather", R.drawable.poster_thegodfather),
        Movie(12, "The thing", R.drawable.poster_thething),
    )
}
