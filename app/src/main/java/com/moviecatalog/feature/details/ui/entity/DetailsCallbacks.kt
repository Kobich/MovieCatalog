package com.moviecatalog.feature.details.ui.entity

import com.moviecatalog.feature.catalog.domain.entity.Category
import com.moviecatalog.feature.catalog.domain.entity.Movie

data class DetailsCallbacks(
    val onPosterClick: (Movie) -> Unit,
    val onBackClick: () -> Unit,
    )

