package com.russialoses.app.work

import android.content.Context
import android.content.Intent
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.russialoses.app.domain.usecase.LossesUseCase
import com.russialoses.app.feature.widget.LossesWidgetReceiver
import com.russialoses.app.feature.widget.LossesWidgetReceiver.Companion.DATA_VALUE
import com.russialoses.app.feature.widget.LossesWidgetReceiver.Companion.UPDATE_ACTION
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UpdateLossesDataWork @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val losesRepository: LossesUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val result = losesRepository.getRussianLosses()

        return if (result.isSuccessful) {
            result.body()?.let {
                val intent = Intent(appContext, LossesWidgetReceiver::class.java).apply {
                    action = UPDATE_ACTION
                    putExtra(DATA_VALUE, result.body()?.toTypedArray())
                }
                appContext.sendBroadcast(intent)
            }
            Result.success()
        } else {
            Result.failure()
        }
    }

}