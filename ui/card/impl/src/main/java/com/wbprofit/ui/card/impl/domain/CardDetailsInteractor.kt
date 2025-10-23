package com.wbprofit.ui.card.impl.domain

import com.wbprofit.feature.cards.api.CardsFeature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

internal class CardDetailsInteractor(
    private val cardsFeature: CardsFeature,
) {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var job: Job? = null

    val state: MutableStateFlow<CardDetailsScreenState> =
        MutableStateFlow(CardDetailsScreenState.Loading)

    fun load(nmId: Long) {
        job?.cancel()
        job = coroutineScope.launch {
            state.value = CardDetailsScreenState.Loading
            runCatching { cardsFeature.getCard(nmId) }
                .onSuccess { card ->
                    state.value = card?.let { CardDetailsScreenState.Success(it) }
                        ?: CardDetailsScreenState.Error(CARD_NOT_FOUND_MESSAGE)
                }
                .onFailure { throwable ->
                    Timber.Forest.e(throwable, "Failed to load card %d", nmId)
                    state.value = CardDetailsScreenState.Error(
                        throwable.message ?: GENERIC_ERROR_MESSAGE,
                    )
                }
        }
    }

    private companion object {
        const val CARD_NOT_FOUND_MESSAGE = "Card not found"
        const val GENERIC_ERROR_MESSAGE = "Unable to load card details"
    }
}
