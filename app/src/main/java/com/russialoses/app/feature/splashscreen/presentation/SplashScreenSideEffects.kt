package com.russialoses.app.feature.splashscreen.presentation

import com.krykun.domain.model.RussianLossesItem

sealed class SplashScreenSideEffects {
    data class UpdateRussianLosses(val russianLosses: List<RussianLossesItem>?) :
        SplashScreenSideEffects()

    object ErrorLoadingRussianLosses : SplashScreenSideEffects()
    object LoadingRussianLosses : SplashScreenSideEffects()
}
