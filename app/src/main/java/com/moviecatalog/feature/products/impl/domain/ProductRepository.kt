package com.moviecatalog.feature.products.impl.domain

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject

interface ProductRepository {
    suspend fun getProductParents(locale: String? = null): List<CategoryParent>
    suspend fun getProductSubjects(
        locale: String? = null,
        name: String? = null,
        limit: Int? = null,
        offset: Int? = null,
        parentId: Int? = null,
    ): List<ProductSubject>
    suspend fun getProductCharacteristics(
        subjectId: Int,
        locale: String? = null,
    ): List<ProductCharacteristic>

    suspend fun getCards(): List<Long>
}
