package com.russialoses.app.data.datasource

import com.russialoses.app.data.api.LosesApiService
import com.russialoses.app.domain.interfaces.LosesDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LossesDataSourceImpl @Inject constructor(
    private val losesApiService: LosesApiService
) : LosesDataSource {

    override suspend fun getLosses() = losesApiService.getLossesData()
}