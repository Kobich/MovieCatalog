package com.moviecatalog.feature.products.impl.domain

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject

class ProductDataInteractor(
    private val repo: ProductRepository
) {
    suspend fun getProductParents(locale: String? = null): List<CategoryParent> {
        return repo.getProductParents(locale)
    }

    suspend fun getProductSubjects(
        locale: String? = null,
        name: String? = null,
        limit: Int? = null,
        offset: Int? = null,
        parentId: Int? = null,
    ): List<ProductSubject> {
        return repo.getProductSubjects(
            locale = locale,
            name = name,
            limit = limit,
            offset = offset,
            parentId = parentId,
        )
    }

    suspend fun getProductCharacteristics(
        subjectId: Int,
        locale: String? = null,
    ): List<ProductCharacteristic> {
        return repo.getProductCharacteristics(
            subjectId = subjectId,
            locale = locale,
        )
    }
}
