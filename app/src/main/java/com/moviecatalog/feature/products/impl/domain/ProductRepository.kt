package com.moviecatalog.feature.products.impl.domain

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject

interface ProductRepository {
    suspend fun getProductParents(): List<CategoryParent>
    suspend fun getProductSubjects(): List<ProductSubject>
    suspend fun getProductCharacteristics(subjectId: Int): List<ProductCharacteristic>
}
