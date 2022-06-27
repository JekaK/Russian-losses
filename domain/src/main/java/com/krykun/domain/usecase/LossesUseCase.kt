package com.krykun.domain.usecase

import com.krykun.domain.repositories.RemoteRepo
import javax.inject.Inject

class LossesUseCase @Inject constructor() {

    @Inject
    lateinit var remoteRepo: RemoteRepo

    suspend fun getRussianLosses() = remoteRepo.getLosses()
}