package com.russialoses.app.domain.interfaces

import com.russialoses.app.domain.model.RussianLossesItem
import retrofit2.Response

interface LosesDataSource {
    suspend fun getLosses(): Response<List<RussianLossesItem>>
}