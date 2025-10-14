package com.moviecatalog.ui.cards.impl.ui.entity

data class CardsCallbacks(
    val onClick: (imtID: Long) -> Unit,
    val onRefresh: () -> Unit,
)
