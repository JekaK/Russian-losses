package com.russialoses.app.domain.usecase

import com.russialoses.app.domain.interfaces.LosesDataSource
import javax.inject.Inject

class LossesUseCase @Inject constructor() {

    @Inject
    lateinit var lossesDataSource: LosesDataSource

    suspend fun getRussianLosses() = lossesDataSource.getLosses()
}