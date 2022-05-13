package com.russialoses.app.data

import com.russialoses.app.model.RussianLossesItem
import retrofit2.Response

interface LosesRepository {
    suspend fun getLosses(): Response<List<RussianLossesItem>>
}