package com.wbprofit.feature.auth.impl.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {
    @GET("/ping")
    suspend fun verifyToken(
        @Header("Authorization") apiKey: String,
    ): Response<Unit>
}
