package com.russialoses.app.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russialoses.app.domain.usecase.LossesUseCase
import com.russialoses.app.ext.takeWhenChanged
import com.russialoses.app.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
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