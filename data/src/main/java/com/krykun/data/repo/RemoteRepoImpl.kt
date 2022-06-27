package com.krykun.data.repo

import com.krykun.data.api.ApiService
import com.krykun.data.mappers.toRussianLossesItem
import com.krykun.domain.model.RussianLossesItem
import com.krykun.domain.repositories.RemoteRepo
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteRepo {

    override suspend fun getLosses(): List<RussianLossesItem> {
        return apiService.getLossesData()
            .map {
                it.toRussianLossesItem()
            }
    }
}