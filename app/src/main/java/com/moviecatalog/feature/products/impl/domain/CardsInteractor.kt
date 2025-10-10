package com.moviecatalog.feature.products.impl.domain

class CardsInteractor(
    private val repo: ProductRepository
) {
    suspend fun getCards(): List<Long> {
        return repo.getCards()
    }
}
