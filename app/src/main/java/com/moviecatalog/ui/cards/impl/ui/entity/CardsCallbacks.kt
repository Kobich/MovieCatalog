package com.moviecatalog.ui.cards.impl.ui.entity

import com.moviecatalog.feature.movies.api.entity.Category

data class CardsCallbacks(
    val onClick: (imtID: Long) -> Unit,
    val onCategoryChange: (category: Category) -> Unit,
)
