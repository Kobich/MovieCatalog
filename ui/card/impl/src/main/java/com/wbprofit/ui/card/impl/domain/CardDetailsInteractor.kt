package com.wbprofit.ui.card.impl.domain


import com.wbprofit.feature.cards.api.CardsFeature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

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
            try {
                val card = cardsFeature.getCard(nmId)
                if (card != null) {
                    state.value = CardDetailsScreenState.Success(card)
                } else {
                    state.value = CardDetailsScreenState.Error("Карточка не найдена")
                }
            } catch (e: Exception) {
                state.value = CardDetailsScreenState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}
