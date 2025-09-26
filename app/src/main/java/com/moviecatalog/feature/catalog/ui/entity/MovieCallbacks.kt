package com.moviecatalog.feature.catalog.ui.entity

import com.moviecatalog.feature.catalog.domain.entity.Category
import com.moviecatalog.feature.catalog.domain.entity.Movie

data class MovieCallbacks(
    val onMovieClick: (Movie) -> Unit,
    val onCategoryChange: (category: Category) -> Unit,
)