package com.moviecatalog.feature.catalog.ui.entity

import com.moviecatalog.feature.catalog.domain.models.Category
import com.moviecatalog.feature.catalog.domain.models.Movie

data class MovieCallbacks(
    val onMovieClick: (Movie) -> Unit,
    val onCategoryChange: (category: Category) -> Unit,
)