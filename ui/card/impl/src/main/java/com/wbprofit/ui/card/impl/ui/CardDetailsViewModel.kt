package com.wbprofit.ui.card.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbprofit.feature.cards.api.entity.CardDetail
import com.wbprofit.ui.card.impl.domain.CardDetailsInteractor
import com.wbprofit.ui.card.impl.domain.CardDetailsScreenState
import com.wbprofit.ui.card.impl.ui.entity.CardCharacteristicViewState
import com.wbprofit.ui.card.impl.ui.entity.CardDetailsUiState
import com.wbprofit.ui.card.impl.ui.entity.CardDetailsViewState
import com.wbprofit.ui.card.impl.ui.entity.CardInfoItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class CardDetailsViewModel(
    nmId: Long,
    private val interactor: CardDetailsInteractor,
) : ViewModel() {

    val uiState: StateFlow<CardDetailsUiState> = interactor.state
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = CardDetailsUiState.Loading,
        )

    init {
        interactor.load(nmId)
    }

    private fun CardDetailsScreenState.toUiState(): CardDetailsUiState {
        return when (this) {
            CardDetailsScreenState.Loading -> CardDetailsUiState.Loading
            is CardDetailsScreenState.Error -> CardDetailsUiState.Error(message)
            is CardDetailsScreenState.Success -> CardDetailsUiState.Content(card.toViewState())
        }
    }

    private fun CardDetail.toViewState(): CardDetailsViewState {
        val images = photos.ifEmpty { emptyList() }
        val infoItems = buildList<CardInfoItem> {
            nmId.takeIf { it != 0L }?.let {
                add(CardInfoItem(title = "Артикул WB", value = "$it"))
            }
            brand?.takeIf { it.isNotBlank() }?.let {
                add(CardInfoItem(title = "Бренд", value = it))
            }
            dimensions?.length?.let {
                add(CardInfoItem(title = "Длина", value = "$it см"))
            }
            dimensions?.width?.let {
                add(CardInfoItem(title = "Ширина", value = "$it см"))
            }
            dimensions?.height?.let {
                add(CardInfoItem(title = "Высота", value = "$it см"))
            }
            dimensions?.weightBrutto?.let {
                add(CardInfoItem(title = "Вес", value = "${it} кг"))
            }
        }

        val characteristicViews = characteristics.mapNotNull { characteristic ->
            val value = characteristic.values.joinToString(separator = ", ") { item -> item.trim() }
            if (characteristic.name.isNotBlank() && value.isNotBlank()) {
                CardCharacteristicViewState(
                    title = characteristic.name,
                    value = value
                )
            } else {
                null
            }
        }

        return CardDetailsViewState(
            nmId = nmId,
            title = title,
            vendorCode = vendorCode,
            brand = brand,
            description = description,
            imageUrls = images,
            infoItems = infoItems,
            characteristics = characteristicViews,
        )
    }
}
