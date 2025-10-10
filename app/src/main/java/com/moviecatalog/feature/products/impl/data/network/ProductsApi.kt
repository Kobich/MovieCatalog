package com.moviecatalog.feature.products.impl.data.network

import com.moviecatalog.feature.products.impl.data.network.dto.CardsRequest
import com.moviecatalog.feature.products.impl.data.network.dto.CardsResponseDto
import com.moviecatalog.feature.products.impl.data.network.dto.CategoryParentsResponse
import com.moviecatalog.feature.products.impl.data.network.dto.ProductCharacteristicsResponse
import com.moviecatalog.feature.products.impl.data.network.dto.ProductSubjectsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit contract describing endpoints that expose products related dictionaries.
 * Only the contract is defined here, concrete configuration is supplied during DI wiring.
 */
interface ProductsApi {

    @GET("/content/v2/object/parent/all")
    suspend fun getCategoryParents(
        @Query("locale") locale: String? = null
    ): CategoryParentsResponse


    @GET("/content/v2/object/all")
    suspend fun getProductSubjects(
        @Query("locale") locale: String? = null,
        @Query("name") name: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("parentID") parentID: Int? = null,
    ): ProductSubjectsResponse

    @GET("/content/v2/object/charcs/{subjectId}")
    suspend fun getProductCharacteristics(
        @Path("subjectId") subjectId: Int,
        @Query("locale") locale: String? = null,
    ): ProductCharacteristicsResponse

    @POST("/content/v2/get/cards/list")
    suspend fun getCards(
        @Body body: CardsRequest
    ): CardsResponseDto
}
