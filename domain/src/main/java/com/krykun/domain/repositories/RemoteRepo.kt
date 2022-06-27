package com.krykun.domain.repositories

import com.krykun.domain.model.RussianLossesItem

interface RemoteRepo {
    suspend fun getLosses(): List<RussianLossesItem>
}