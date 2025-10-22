package com.wbprofit.ui.main.impl.ui.entity

sealed class TabItem(
    val route: String,
    val label: String,
    val badgeText: String,
) {
    data object Catalog : TabItem(
        route = "catalog",
        label = "Каталог",
        badgeText = "К",
    )

    data object Analytics : TabItem(
        route = "analytics",
        label = "Аналитика",
        badgeText = "А",
    )

    companion object {
        val items = listOf(Catalog, Analytics)
    }
}
