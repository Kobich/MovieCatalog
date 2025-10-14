package com.moviecatalog.feature.cards.api.entity

data class Card(
    val id: Long,
    val imtID: Long,
    val title: String?,
    val imageUrl: String?,
)
