package com.moviecatalog.ui.card.impl.ui.entity

internal sealed class CardDetailsUiState {
    data object Loading : CardDetailsUiState()
    data class Content(val state: CardDetailsViewState) : CardDetailsUiState()
    data class Error(val message: String) : CardDetailsUiState()
}

internal data class CardDetailsViewState(
    val id: Long,
    val title: String,
    val vendorCode: String?,
    val brand: String?,
    val description: String?,
    val imageUrls: List<String>,
    val infoItems: List<CardInfoItem>,
    val characteristics: List<CardCharacteristicViewState>,
)

internal data class CardInfoItem(
    val title: String,
    val value: String,
)

internal data class CardCharacteristicViewState(
    val title: String,
    val value: String,
)
