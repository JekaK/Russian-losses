package com.russialoses.app.presentation.feature.splashscreen.presentation

data class SplashScreenState(
    val isOpen: Boolean = false,
    val dataLoadingState: DataLoadingState = DataLoadingState.DEFAULT
)

enum class DataLoadingState {
    DEFAULT,
    LOADING,
    ERROR
}
