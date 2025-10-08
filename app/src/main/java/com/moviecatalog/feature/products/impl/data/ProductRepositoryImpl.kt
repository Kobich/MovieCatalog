package com.moviecatalog.feature.products.impl.data

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject
import com.moviecatalog.feature.products.impl.data.datasource.ProductsNetworkDataSource
import com.moviecatalog.feature.products.impl.domain.ProductRepository

class ProductRepositoryImpl(
    private val networkDataSource: ProductsNetworkDataSource
) : ProductRepository {

    override suspend fun getProductParents(): List<CategoryParent> {
        return networkDataSource.getCategoryParents()
    }

    override suspend fun getProductSubjects(): List<ProductSubject> {
        return networkDataSource.getProductSubjects()
    }

    override suspend fun getProductCharacteristics(subjectId: Int): List<ProductCharacteristic> {

        return networkDataSource.getProductCharacteristics(subjectId)
    }
}
