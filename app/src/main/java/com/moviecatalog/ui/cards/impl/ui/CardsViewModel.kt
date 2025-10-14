package com.moviecatalog.ui.cards.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.ui.cards.impl.domain.CardsInteractor
import com.moviecatalog.ui.cards.impl.domain.entity.CardsScreenState
import com.moviecatalog.ui.cards.impl.ui.entity.CardViewState
import com.moviecatalog.ui.cards.impl.ui.entity.CardsScreenViewState
import com.moviecatalog.ui.cards.impl.ui.entity.CardsViewState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CardsViewModel(
    private val interactor: CardsInteractor,
) : ViewModel() {

    val uiState: StateFlow<CardsScreenViewState> = interactor.state
        .map { it.map() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = CardsScreenViewState.Loading,
        )

    init {
        interactor.init()
    }

    fun refresh() {
        interactor.refresh()
    }

    private fun CardsScreenState.map(): CardsScreenViewState {
        return when (this) {
            is CardsScreenState.Loading -> CardsScreenViewState.Loading
            is CardsScreenState.Error -> CardsScreenViewState.Error(message)
            is CardsScreenState.Success -> CardsScreenViewState.Content(
                CardsViewState(
                    cards = cardsState.cards.map { card ->
                        CardViewState(
                            id = card.id,
                            imtID = card.imtID,
                            title = card.title.orEmpty(),
                            imageUrl = card.imageUrl
                        )
                    }
                )
            )
        }
    }
}
