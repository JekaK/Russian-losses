package com.russialoses.app.feature.home.presentation

import com.russialoses.app.domain.model.RussianLossesItem

data class HomeState(
    val isOpen: Boolean = false,
    val russianLosses: List<RussianLossesItem>? = null,
)