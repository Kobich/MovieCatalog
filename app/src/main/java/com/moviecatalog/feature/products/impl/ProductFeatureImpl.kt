package com.moviecatalog.feature.products.impl

import com.moviecatalog.feature.products.api.ProductFeature
import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject
import com.moviecatalog.feature.products.impl.domain.ProductDataInteractor


class ProductFeatureImpl(
    private val productsDataInteractor: ProductDataInteractor
) : ProductFeature {

    override suspend fun getProductParents(locale: String?): List<CategoryParent> {
        return productsDataInteractor.getProductParents(locale)
    }

    override suspend fun getProductSubjects(
        locale: String?,
        name: String?,
        limit: Int?,
        offset: Int?,
        parentId: Int?,
    ): List<ProductSubject> {
        return productsDataInteractor.getProductSubjects(
            locale = locale,
            name = name,
            limit = limit,
            offset = offset,
            parentId = parentId,
        )
    }

    override suspend fun getProductCharacteristics(
        subjectId: Int,
        locale: String?,
    ): List<ProductCharacteristic> {
        return productsDataInteractor.getProductCharacteristics(
            subjectId = subjectId,
            locale = locale,
        )
    }
}
