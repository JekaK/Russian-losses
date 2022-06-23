package com.russialoses.app.presentation.state

import com.russialoses.app.presentation.feature.home.presentation.HomeState
import com.russialoses.app.presentation.feature.splashscreen.presentation.SplashScreenState

data class AppState(
    val splashScreenState: SplashScreenState = SplashScreenState(),
    val homeState: HomeState = HomeState()
)
