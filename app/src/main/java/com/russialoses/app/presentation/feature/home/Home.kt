package com.russialoses.app.presentation.feature.home

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russialoses.app.R
import com.russialoses.app.domain.model.RussianLossesItem
import com.russialoses.app.presentation.feature.home.presentation.HomeViewModel
import com.russialoses.app.presentation.feature.home.view.AdditionalInfoView
import com.russialoses.app.presentation.feature.home.view.BarChartView
import com.russialoses.app.presentation.feature.home.view.FullLossesView
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
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                delayMillis = 5000,
                durationMillis = 200,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val context = LocalContext.current
    val intent =
        remember {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://savelife.in.ua/donate/")
            )
        }
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
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState(), true)
                    .fillMaxSize()
            ) {
                if (russianLosses.isNotEmpty()) {
                    FullLossesView(
                        current = russianLosses.first().personnel ?: 0,
                        previous = russianLosses[1]?.personnel ?: 0
                    )
                }
                BarChartView(russianLosses = russianLosses)
                AdditionalInfoView(russianLosses = russianLosses)
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
                    .scale(scale)
                    .align(Alignment.BottomEnd),
                content = {
                    Row(
                        modifier = Modifier.background(Color.Red),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Outlined.Shield,
                            contentDescription = "",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.donate).uppercase(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }, backgroundColor = Color.Red,
                onClick = {
                    context.startActivity(intent)
                })
        }
    }
}
