package com.russialoses.app.domain.usecase

import com.russialoses.app.domain.repositories.RemoteRepo
import javax.inject.Inject

class LossesUseCase @Inject constructor() {

    @Inject
    lateinit var remoteRepo: RemoteRepo

    suspend fun getRussianLosses() = remoteRepo.getLosses()
}