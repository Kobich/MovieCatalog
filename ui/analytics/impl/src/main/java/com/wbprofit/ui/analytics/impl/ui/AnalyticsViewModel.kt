package com.wbprofit.ui.analytics.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbprofit.feature.analytics.api.entity.AnalyticsDate
import com.wbprofit.feature.analytics.api.entity.SalesItem
import com.wbprofit.ui.analytics.impl.domain.AnalyticsInteractor
import com.wbprofit.ui.analytics.impl.domain.entity.AnalyticsScreenState
import com.wbprofit.ui.analytics.impl.ui.entity.AnalyticsItemViewState
import com.wbprofit.ui.analytics.impl.ui.entity.AnalyticsScreenViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class AnalyticsViewModel(
    private val interactor: AnalyticsInteractor,
) : ViewModel() {
    private val _dateInput = MutableStateFlow("")
    val dateInput: StateFlow<String> = _dateInput.asStateFlow()

    private val _dateError = MutableStateFlow<String?>(null)
    val dateError: StateFlow<String?> = _dateError.asStateFlow()

    val uiState: StateFlow<AnalyticsScreenViewState> = interactor.state
        .map(::mapToViewState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = AnalyticsScreenViewState.Loading,
        )

    init {
        interactor.init()
    }

    fun refresh() {
        interactor.refresh()
    }

    fun onDateChanged(value: String) {
        _dateInput.value = value
        _dateError.value = null
    }

    fun applyDateFilter() {
        val trimmed = dateInput.value.trim()
        val date = runCatching { AnalyticsDate(trimmed) }.getOrNull()
        if (date == null) {
            _dateError.value = DATE_ERROR_MESSAGE
        } else {
            _dateError.value = null
            _dateInput.value = date.value
            interactor.refreshForDate(date)
        }
    }

    private fun mapToViewState(state: AnalyticsScreenState): AnalyticsScreenViewState =
        when (state) {
            AnalyticsScreenState.Loading -> AnalyticsScreenViewState.Loading
            is AnalyticsScreenState.Error -> AnalyticsScreenViewState.Error(state.message)
            is AnalyticsScreenState.Success -> {
                _dateInput.value = state.report.params.dateFrom.value
                AnalyticsScreenViewState.Content(
                    periodLabel = state.report.params.dateFrom.value,
                    items = state.report.items.map(::mapItem),
                )
            }
        }

    private fun mapItem(item: SalesItem): AnalyticsItemViewState = AnalyticsItemViewState(
        nmId = item.nmId,
        supplierArticle = item.supplierArticle,
        subject = item.subject,
        brand = item.brand,
        techSize = item.techSize,
        soldCount = item.quantity,
        returnCount = item.returns,
        operationsCount = item.operations,
        buyoutPercent = item.buyoutPercent,
        grossRevenue = item.grossRevenue,
        netRevenue = item.netRevenue,
        payout = item.payout,
    )

    private companion object {
        const val DATE_ERROR_MESSAGE = "Используйте формат yyyy-MM-dd"
    }
}
