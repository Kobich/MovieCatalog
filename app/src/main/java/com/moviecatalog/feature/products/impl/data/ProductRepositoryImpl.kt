package com.moviecatalog.feature.products.impl.data

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject
import com.moviecatalog.feature.products.impl.data.network.mapper.toDomain
import com.moviecatalog.feature.products.impl.data.network.ProductsApi
import com.moviecatalog.feature.products.impl.domain.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val api: ProductsApi,
) : ProductRepository {

    override suspend fun getProductParents(): List<CategoryParent> {
        return withContext(Dispatchers.IO) {
            api.getCategoryParents()
                .items
                .map { it.toDomain() }
        }
    }

    override suspend fun getProductSubjects(): List<ProductSubject> {
        return  withContext(Dispatchers.IO) {
            api.getProductSubjects()
                .items
                .map { it.toDomain() }
        }
    }

    override suspend fun getProductCharacteristics(subjectId: Int): List<ProductCharacteristic> {
        return  withContext(Dispatchers.IO) {
            api.getProductCharacteristics(subjectId)
                .items
                .map { it.toDomain() }
        }
    }
}
