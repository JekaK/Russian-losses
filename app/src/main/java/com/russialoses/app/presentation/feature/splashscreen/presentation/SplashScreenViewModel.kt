package com.russialoses.app.presentation.feature.splashscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krykun.domain.usecase.LossesUseCase
import com.russialoses.app.presentation.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
    private val lossesUseCase: LossesUseCase
) : ContainerHost<MutableStateFlow<AppState>, SplashScreenSideEffects>,
    ViewModel() {

    override val container =
        container<MutableStateFlow<AppState>, SplashScreenSideEffects>(appState)

    init {
        updateRussianLosses()
    }

    private fun updateRussianLosses() = intent {
        reduce {
            state.value = state.value.copy(
                splashScreenState = state.value.splashScreenState.copy(dataLoadingState = DataLoadingState.LOADING)
            )
            state
        }
        postSideEffect(SplashScreenSideEffects.LoadingRussianLosses)

        viewModelScope.launch {
            val result = async { lossesUseCase.getRussianLosses() }.await()

            if (result.isNotEmpty()) {
                reduce {
                    state.value = state.value.copy(
                        homeState = state.value.homeState.copy(
                            russianLosses = result,
                        ),
                        splashScreenState = state.value.splashScreenState.copy(dataLoadingState = DataLoadingState.DEFAULT)

                    )
                    state
                }
                postSideEffect(SplashScreenSideEffects.UpdateRussianLosses(result))
            } else {
                reduce {
                    state.value = state.value.copy(
                        homeState = state.value.homeState.copy(
                            russianLosses = null,
                        ),
                        splashScreenState = state.value.splashScreenState.copy(dataLoadingState = DataLoadingState.ERROR)
                    )
                    state
                }
                postSideEffect(SplashScreenSideEffects.ErrorLoadingRussianLosses)
            }
        }
    }

}