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

    override suspend fun getProductParents(locale: String?): List<CategoryParent> {
        return withContext(Dispatchers.IO) {
            api.getCategoryParents(locale = locale)
                .items
                .map { it.toDomain() }
        }
    }

    override suspend fun getProductSubjects(
        locale: String?,
        name: String?,
        limit: Int?,
        offset: Int?,
        parentId: Int?,
    ): List<ProductSubject> {
        return withContext(Dispatchers.IO) {
            api.getProductSubjects(
                locale = locale,
                name = name,
                limit = limit,
                offset = offset,
                parentID = parentId,
            )
                .items
                .map { it.toDomain() }
        }
    }

    override suspend fun getProductCharacteristics(
        subjectId: Int,
        locale: String?,
    ): List<ProductCharacteristic> {
        return withContext(Dispatchers.IO) {
            api.getProductCharacteristics(
                subjectId = subjectId,
                locale = locale,
            )
                .items
                .map { it.toDomain() }
        }
    }
}
