package com.moviecatalog.ui.catalog.ui.entity

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

data class MovieCallbacks(
    val onMovieClick: (Movie) -> Unit,
    val onCategoryChange: (category: Category) -> Unit,
)