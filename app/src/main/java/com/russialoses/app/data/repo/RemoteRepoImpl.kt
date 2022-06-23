package com.russialoses.app.data.repo

import com.russialoses.app.data.api.ApiService
import com.russialoses.app.data.mappers.toRussianLossesItem
import com.russialoses.app.domain.model.RussianLossesItem
import com.russialoses.app.domain.repositories.RemoteRepo
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