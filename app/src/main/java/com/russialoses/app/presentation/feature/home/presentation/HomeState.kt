package com.russialoses.app.presentation.feature.home.presentation

import com.krykun.domain.model.RussianLossesItem

data class HomeState(
    val isOpen: Boolean = false,
    val russianLosses: List<RussianLossesItem>? = null,
)