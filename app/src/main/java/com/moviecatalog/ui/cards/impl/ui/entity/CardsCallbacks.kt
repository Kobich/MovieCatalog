package com.moviecatalog.ui.cards.impl.ui.entity

import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie

data class CardsCallbacks(
    val onClick: (CardViewState) -> Unit,
    val onCategoryChange: (category: Category) -> Unit,
)