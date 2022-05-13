package com.russialoses.app.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russialoses.app.data.LosesRepository
import com.russialoses.app.model.RussianLossesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val losesRepository: LosesRepository
) : ViewModel() {

    private val _losesReponse: MutableStateFlow<List<RussianLossesItem>> = MutableStateFlow(
        emptyList()
    )

    fun getRussianLoses(): StateFlow<List<RussianLossesItem>> {
        return _losesReponse
    }

    init {
        viewModelScope.launch {
            val result = async { losesRepository.getLosses() }.await()

            if (result.isSuccessful) {
                result.body()?.let {
                    _losesReponse.value = it
                }
            } else {
                _losesReponse.value = emptyList()
            }
        }
    }

}