package com.moviecatalog.ui.details.impl.ui.entity

import com.moviecatalog.feature.movies.api.entity.Movie

internal data class DetailsCallbacks(
    val onPosterClick: (Movie) -> Unit,
    val onBackClick: () -> Unit,
    )

