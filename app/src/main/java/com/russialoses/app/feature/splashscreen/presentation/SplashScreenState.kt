package com.russialoses.app.feature.splashscreen.presentation

data class SplashScreenState(
    val isOpen: Boolean = false,
    val dataLoadingState: DataLoadingState = DataLoadingState.DEFAULT
)

enum class DataLoadingState {
    DEFAULT,
    LOADING,
    ERROR
}
