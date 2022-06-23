package com.russialoses.app.data.api

import com.russialoses.app.data.model.RussianLossesDataItem
import com.russialoses.app.domain.model.RussianLossesItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("los")
    suspend fun getLossesData(): List<RussianLossesDataItem>
}