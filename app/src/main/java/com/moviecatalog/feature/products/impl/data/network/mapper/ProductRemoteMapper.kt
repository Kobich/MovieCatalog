package com.moviecatalog.feature.products.impl.data.network.mapper

import com.moviecatalog.feature.products.api.entity.CategoryParent
import com.moviecatalog.feature.products.api.entity.ProductCharacteristic
import com.moviecatalog.feature.products.api.entity.ProductSubject
import com.moviecatalog.feature.products.impl.data.network.dto.CategoryParentDto
import com.moviecatalog.feature.products.impl.data.network.dto.ProductCharacteristicDto
import com.moviecatalog.feature.products.impl.data.network.dto.ProductSubjectDto

internal fun CategoryParentDto.toDomain(): CategoryParent {
    return CategoryParent(
        name = name,
        id = id,
        isVisible = isVisible
    )
}

internal fun ProductSubjectDto.toDomain(): ProductSubject {
    return ProductSubject(
        subjectID = subjectId,
        parentID = parentId,
        subjectName = subjectName,
        parentName = parentName
    )
}

internal fun ProductCharacteristicDto.toDomain(): ProductCharacteristic {
    return ProductCharacteristic(
        charcId = characteristicId,
        subjectId = subjectId,
        subjectName = subjectName,
        name = name,
        unitName = unitName,
        required = isRequired,
        maxCount = maxCount,
        popular = isPopular,
        charcType = type
    )
}
