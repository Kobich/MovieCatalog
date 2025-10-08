package com.moviecatalog.feature.products.impl.data.network

import com.moviecatalog.feature.products.impl.data.network.dto.CategoryParentsResponse
import com.moviecatalog.feature.products.impl.data.network.dto.ProductCharacteristicsResponse
import com.moviecatalog.feature.products.impl.data.network.dto.ProductSubjectsResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit contract describing endpoints that expose products related dictionaries.
 * Only the contract is defined here, concrete configuration is supplied during DI wiring.
 */
interface ProductsApi {

    @GET("/content/v2/object/parent/all")
    suspend fun getCategoryParents(): CategoryParentsResponse

    @GET("/content/v2/object/all")
    suspend fun getProductSubjects(): ProductSubjectsResponse

    @GET("/content/v2/object/charcs/{subjectId}")
    suspend fun getProductCharacteristics(
        @Path("subjectId") subjectId: Int
    ): ProductCharacteristicsResponse
}
