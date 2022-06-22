package com.russialoses.app.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russialoses.app.domain.model.RussianLossesItem
import com.russialoses.app.domain.usecase.LossesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val lossesUseCase: LossesUseCase
) : ViewModel() {

    private val _losesResponse: MutableStateFlow<List<RussianLossesItem>> = MutableStateFlow(
        emptyList()
    )

    fun getRussianLoses(): StateFlow<List<RussianLossesItem>> {
        return _losesResponse
    }

    init {
        viewModelScope.launch {
            val result = async { lossesUseCase.getRussianLosses() }.await()

            if (result.isSuccessful) {
                result.body()?.let {
                    _losesResponse.value = it
                }
            } else {
                _losesResponse.value = emptyList()
            }
        }
    }

}