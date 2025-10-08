package com.moviecatalog.feature.products.impl.data.remote.datasource

import com.moviecatalog.feature.products.impl.data.remote.model.CategoryParentDto
import com.moviecatalog.feature.products.impl.data.remote.model.ProductCharacteristicDto
import com.moviecatalog.feature.products.impl.data.remote.model.ProductSubjectDto
import com.moviecatalog.feature.products.impl.data.remote.service.ProductsApi

/**
 * Thin wrapper above [ProductsApi] that exposes only DTO collections and keeps request level
 * parameters consistent across the feature.
 */
class ProductsRemoteDataSource(
    private val api: ProductsApi
) {

    suspend fun getCategoryParents(): List<CategoryParentDto> {
        return api.getCategoryParents().items
    }

    suspend fun getProductSubjects(): List<ProductSubjectDto> {
        return api.getProductSubjects().items
    }

    suspend fun getProductCharacteristics(subjectId: Int): List<ProductCharacteristicDto> {
        return api.getProductCharacteristics(subjectId = subjectId).items
    }
}
