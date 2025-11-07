package com.wbprofit.feature.analytics.impl.domain.model

internal data class SaleRecord(
    val nmId: Long,
    val supplierArticle: String,
    val subject: String,
    val brand: String,
    val techSize: String,
    val totalPrice: Double,
    val paymentSaleAmount: Double,
    val forPay: Double,
    val isReturn: Boolean,
)

internal data class SaleAggregate(
    val nmId: Long,
    val supplierArticle: String,
    val subject: String,
    val brand: String,
    val techSize: String,
    val soldCount: Int,
    val returnCount: Int,
    val operationsCount: Int,
    val buyoutPercent: Double,
    val grossRevenue: Double,
    val netRevenue: Double,
    val payout: Double,
)

// Groups raw sale rows by catalog item (nmId + supplier article + size) and enriches them
// with counts for sales vs returns plus derived buyout percentage.
internal fun Collection<SaleRecord>.aggregateByItem(): List<SaleAggregate> =
    groupBy { SaleAggregateKey(it.nmId, it.supplierArticle, it.techSize) }
        .map { (key, groupedSales) ->
            val firstSale = groupedSales.first()
            val operationsCount = groupedSales.size
            val returnCount = groupedSales.count(SaleRecord::isReturn)
            val soldCount = operationsCount - returnCount
            val buyoutPercent = if (operationsCount == 0) {
                0.0
            } else {
                (soldCount.toDouble() / operationsCount) * 100.0
            }
            SaleAggregate(
                nmId = key.nmId,
                supplierArticle = key.supplierArticle,
                subject = firstSale.subject,
                brand = firstSale.brand,
                techSize = key.techSize,
                soldCount = soldCount,
                returnCount = returnCount,
                operationsCount = operationsCount,
                buyoutPercent = buyoutPercent,
                grossRevenue = groupedSales.sumOf(SaleRecord::totalPrice),
                netRevenue = groupedSales.sumOf(SaleRecord::paymentSaleAmount),
                payout = groupedSales.sumOf(SaleRecord::forPay),
            )
        }

private data class SaleAggregateKey(
    val nmId: Long,
    val supplierArticle: String,
    val techSize: String,
)
