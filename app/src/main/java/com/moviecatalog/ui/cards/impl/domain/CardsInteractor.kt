package com.moviecatalog.ui.cards.impl.domain

import com.example.feature.cards.api.CardsFeature
import com.moviecatalog.ui.cards.impl.domain.entity.CardsScreenState
import com.moviecatalog.ui.cards.impl.domain.entity.CardsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CardsInteractor(
    private val cardsFeature: CardsFeature,
) {

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    val state: MutableStateFlow<CardsScreenState> =
        MutableStateFlow(CardsScreenState.Loading)

    fun init() {
        refresh()
    }

    fun refresh() {
        job?.cancel()
        job = coroutineScope.launch {
            state.value = CardsScreenState.Loading
            try {
                val cards = cardsFeature.getCards()
                state.value = CardsScreenState.Success(
                    CardsState(
                        cards = cards,
                    )
                )
            } catch (e: Exception) {
                state.value = CardsScreenState.Error(e.message ?: "Unknown error")
            }
        }
    }




}