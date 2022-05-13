package com.russialoses.app.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.russialoses.app.R
import com.russialoses.app.feature.home.view.AdditionalInfoView
import com.russialoses.app.feature.home.view.BarChartView
import com.russialoses.app.feature.home.view.FullLossesView
import com.russialoses.app.feature.home.viewmodel.HomeViewModel
import com.russialoses.app.model.RussianLossesItem
import com.russialoses.app.util.collectAsStateLifecycleAware

@Composable
fun Home() {
    val homeViewModel: HomeViewModel = hiltViewModel()

    val russianLosesResponse: List<RussianLossesItem> by homeViewModel.getRussianLoses()
        .collectAsStateLifecycleAware(
            initial = emptyList()
        )
    HomeContent(russianLosesResponse)
}

@Composable
fun HomeContent(russianLosses: List<RussianLossesItem>) {

    Surface {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.gradient_top),
                            colorResource(id = R.color.gradient_bottom)
                        )
                    )
                )
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState(), true)) {
                if (russianLosses.isNotEmpty()) {
                    FullLossesView(
                        current = russianLosses.first().personnel ?: 0,
                        previous = russianLosses[1]?.personnel ?: 0
                    )
                }
                BarChartView(russianLosses = russianLosses)
                AdditionalInfoView(russianLosses = russianLosses)
            }
        }
    }
}