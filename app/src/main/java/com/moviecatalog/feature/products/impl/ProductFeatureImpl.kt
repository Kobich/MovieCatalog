package com.moviecatalog.feature.products.impl

import com.moviecatalog.feature.products.api.ProductFeature
import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject
import com.moviecatalog.feature.products.impl.domain.ProductDataInteractor


class ProductFeatureImpl(
    private val productsDataInteractor: ProductDataInteractor
) : ProductFeature {

    override suspend fun getProductParents(): List<CategoryParent> {
        return productsDataInteractor.getProductParents()
    }
    override suspend fun getProductSubjects(): List<ProductSubject> {
        return productsDataInteractor.getProductSubjects()
    }
    override suspend fun getProductCharacteristics(subjectId: Int): List<ProductCharacteristic> {
        return productsDataInteractor.getProductCharacteristics(subjectId)
    }
}
