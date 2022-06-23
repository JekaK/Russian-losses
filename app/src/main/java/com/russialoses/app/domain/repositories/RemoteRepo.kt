package com.russialoses.app.domain.repositories

import com.russialoses.app.domain.model.RussianLossesItem
import retrofit2.Response

interface RemoteRepo {
    suspend fun getLosses(): List<RussianLossesItem>
}