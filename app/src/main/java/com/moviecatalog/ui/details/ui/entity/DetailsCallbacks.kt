package com.moviecatalog.ui.details.ui.entity

import com.moviecatalog.feature.movies.api.entity.Movie

data class DetailsCallbacks(
    val onPosterClick: (Movie) -> Unit,
    val onBackClick: () -> Unit,
    )

