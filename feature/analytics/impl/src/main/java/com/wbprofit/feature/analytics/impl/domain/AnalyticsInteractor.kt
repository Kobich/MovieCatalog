package com.wbprofit.feature.analytics.impl.domain

import com.wbprofit.feature.analytics.api.entity.SalesItem
import com.wbprofit.feature.analytics.api.entity.SalesParams
import com.wbprofit.feature.analytics.api.entity.SalesReport
import com.wbprofit.feature.analytics.impl.domain.model.aggregateByItem

internal class AnalyticsInteractor(
    private val repository: AnalyticsRepository,
) {
    suspend fun getSalesReport(params: SalesParams): SalesReport {
        val records = repository.getSales(params)
        if (records.isEmpty()) {
            return SalesReport(params = params, items = emptyList())
        }

        val items = records
            .aggregateByItem()
            .map { aggregate ->
                SalesItem(
                    nmId = aggregate.nmId,
                    supplierArticle = aggregate.supplierArticle,
                    subject = aggregate.subject,
                    brand = aggregate.brand,
                    techSize = aggregate.techSize,
                    quantity = aggregate.soldCount,
                    operations = aggregate.operationsCount,
                    returns = aggregate.returnCount,
                    buyoutPercent = aggregate.buyoutPercent,
                    grossRevenue = aggregate.grossRevenue,
                    netRevenue = aggregate.netRevenue,
                    payout = aggregate.payout,
                )
            }
            .sortedByDescending(SalesItem::netRevenue)

        return SalesReport(
            params = params,
            items = items,
        )
    }
}
