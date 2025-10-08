package com.moviecatalog.feature.products.impl.data.datasource

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject
import com.moviecatalog.feature.products.impl.data.mapper.toDomain
import com.moviecatalog.feature.products.impl.data.remote.datasource.ProductsRemoteDataSource

/**
 * Bridges raw remote DTOs with feature entities. Downstream consumers do not need to know
 * about DTO layer or remote specifics.
 */
class ProductsNetworkDataSource(
    private val remoteDataSource: ProductsRemoteDataSource
) {

    suspend fun getCategoryParents(): List<CategoryParent> {
        return remoteDataSource
            .getCategoryParents()
            .map { it.toDomain() }
    }

    suspend fun getProductSubjects(): List<ProductSubject> {
        return remoteDataSource
            .getProductSubjects()
            .map { it.toDomain() }
    }

    suspend fun getProductCharacteristics(subjectId: Int): List<ProductCharacteristic> {
        return remoteDataSource
            .getProductCharacteristics(subjectId = subjectId)
            .map { it.toDomain() }
    }
}
