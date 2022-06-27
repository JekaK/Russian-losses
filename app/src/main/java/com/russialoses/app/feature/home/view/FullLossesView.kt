package com.russialoses.app.feature.home.view

import android.text.format.Time
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russialoses.app.R
import com.russialoses.app.util.DateMapper

@Composable
fun FullLossesView(current: Int, previous: Int) {
    val today = Time(Time.getCurrentTimezone())
    today.setToNow()
    val day = today.monthDay

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append(
                        stringResource(R.string.losses_at)
                    )
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("$day ${DateMapper.getCurrentUkrainianMonth()} ")
                    }
                    append("| ")
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("${DateMapper.getCurrentDayOfWar()}")
                    }
                    append(stringResource(R.string.day_of_war))
                },
                color = colorResource(id = R.color.secondary_text),
            )
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Row {
                    var losses by remember { mutableStateOf(0) }
                    val lossesCounter by animateIntAsState(
                        targetValue = losses,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )
                    Text(
                        text = "≈ $lossesCounter",
                        color = Color.White,
                        fontSize = 60.sp
                    )
                    Text(
                        text = "+${current - previous}",
                        color = colorResource(id = R.color.secondary_text),
                        fontSize = 20.sp
                    )
                    LaunchedEffect(Unit) {
                        losses = current
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FullPreview() {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        FullLossesView(1, 1)
    }
}
