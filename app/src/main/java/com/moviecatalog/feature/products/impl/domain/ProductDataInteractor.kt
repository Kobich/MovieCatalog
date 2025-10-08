package com.moviecatalog.feature.products.impl.domain

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject

class ProductDataInteractor(
    private val repo: ProductRepository
) {
    suspend fun getProductParents(): List<CategoryParent> {
        return repo.getProductParents()
    }
    suspend fun getProductSubjects(): List<ProductSubject> {
        return repo.getProductSubjects()
    }
    suspend fun getProductCharacteristics(subjectId: Int): List<ProductCharacteristic> {
        return repo.getProductCharacteristics(subjectId)
    }
}
