package com.russialoses.app.feature.splashscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.russialoses.app.R
import com.russialoses.app.feature.splashscreen.presentation.SplashScreenSideEffects
import com.russialoses.app.feature.splashscreen.presentation.SplashScreenViewModel
import com.russialoses.app.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AnimatedSplashScreen(
    navHostController: NavHostController,
    viewModel: SplashScreenViewModel
) {
    val scope = rememberCoroutineScope()

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    Splash(alphaAnim.value)
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    viewModel.collectSideEffect {
        handleSideEffects(
            sideEffects = it,
            navHostController = navHostController,
            scope = scope
        )
    }
}

fun handleSideEffects(
    sideEffects: SplashScreenSideEffects,
    navHostController: NavHostController,
    scope: CoroutineScope
) {
    when (sideEffects) {
        is SplashScreenSideEffects.UpdateRussianLosses -> {
            scope.launch {
                delay(1000)
                navHostController.popBackStack()
                navHostController.navigate(Screen.Home.route)
            }
        }
        is SplashScreenSideEffects.LoadingRussianLosses -> {

        }
        is SplashScreenSideEffects.ErrorLoadingRussianLosses -> {

        }
    }
}


@Composable
fun Splash(alpha: Float) {
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
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alpha),
                painter = painterResource(id = R.drawable.ic_emblem_of_the_defence_intelligence_of_ukraine),
                contentDescription = "Logo icon",
            )
            Text(
                text = stringResource(R.string.slava_ukraini),
                modifier = Modifier
                    .alpha(alpha)
                    .padding(top = 20.dp),
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }
}

