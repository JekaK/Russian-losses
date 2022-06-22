package com.russialoses.app.state

import com.russialoses.app.feature.home.presentation.HomeState
import com.russialoses.app.feature.splashscreen.presentation.SplashScreenState

data class AppState(
    val splashScreenState: SplashScreenState = SplashScreenState(),
    val homeState: HomeState = HomeState()
)
