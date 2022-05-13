package com.russialoses.app.data.api

import com.russialoses.app.model.RussianLossesItem
import retrofit2.Response
import retrofit2.http.GET

interface LosesApiService {

    @GET("los")
    suspend fun getLossesData(): Response<List<RussianLossesItem>>
}