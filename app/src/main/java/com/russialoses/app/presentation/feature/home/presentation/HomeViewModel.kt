package com.russialoses.app.presentation.feature.home.presentation

import androidx.lifecycle.ViewModel
import com.russialoses.app.domain.usecase.LossesUseCase
import com.russialoses.app.presentation.ext.takeWhenChanged
import com.russialoses.app.presentation.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
    private val lossesUseCase: LossesUseCase
) : ContainerHost<MutableStateFlow<AppState>, HomeSideEffects>,
    ViewModel() {

    override val container = container<MutableStateFlow<AppState>, HomeSideEffects>(appState)

    init {
        setIsScreenOpen()
    }

    private fun setIsScreenOpen() = intent {
        reduce {
            state.value = state.value.copy(
                homeState = state.value.homeState.copy(isOpen = true)
            )
            state
        }
    }

    fun subscribeToStateUpdate() =
        container.stateFlow.value.takeWhenChanged {
            it.homeState.russianLosses
        }

}