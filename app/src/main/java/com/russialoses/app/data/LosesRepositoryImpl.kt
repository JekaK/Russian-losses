package com.russialoses.app.data

import com.russialoses.app.data.api.LosesApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LosesRepositoryImpl @Inject constructor(private val losesApiService: LosesApiService) :
    LosesRepository {

    override suspend fun getLosses() = losesApiService.getLossesData()
}