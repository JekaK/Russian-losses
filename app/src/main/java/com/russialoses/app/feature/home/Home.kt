package com.russialoses.app.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.russialoses.app.R
import com.russialoses.app.domain.model.RussianLossesItem
import com.russialoses.app.feature.home.presentation.HomeViewModel
import com.russialoses.app.feature.home.view.AdditionalInfoView
import com.russialoses.app.feature.home.view.BarChartView
import com.russialoses.app.feature.home.view.FullLossesView
import kotlinx.coroutines.launch

@Composable
fun Home(viewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()

    var russianLosses by remember {
        mutableStateOf<List<RussianLossesItem>?>(null)
    }

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
        russianLosses?.let { HomeContent(it) }
    }

    LaunchedEffect(key1 = true) {
        scope.launch {
            viewModel.subscribeToStateUpdate().collect {
                if (it?.isNotEmpty() == true) {
                    russianLosses = it
                }
            }
        }
    }
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
