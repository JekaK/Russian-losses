package com.krykun.data.api

import com.krykun.data.model.RussianLossesDataItem
import retrofit2.http.GET

interface ApiService {

    @GET("los")
    suspend fun getLossesData(): List<RussianLossesDataItem>
}