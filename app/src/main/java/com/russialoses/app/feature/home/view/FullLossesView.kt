package com.russialoses.app.feature.home.view

import android.text.format.Time
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russialoses.app.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@Composable
fun FullLossesView(current: Int, previous: Int) {
    val today = Time(Time.getCurrentTimezone())
    today.setToNow()
    val day = today.monthDay
    Column(
        modifier = Modifier.padding(top = 20.dp, start = 20.dp)
    ) {
        Text(
            buildAnnotatedString {
                append(
                    "Втрати особового складу ворога \nна "
                )
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("$day ${DateMapper.getCurrentUkrainianMonth()} ")
                }
                append("| ")
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("${DateMapper.getCurrentDayOfWar()}")
                }
                append(" день війни")
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

object DateMapper {
    fun getCurrentUkrainianMonth(): String {
        val today = Time(Time.getCurrentTimezone())
        today.setToNow()
        val month = today.month
        return when (month) {
            0 -> "січня"
            1 -> "лютого"
            2 -> "березня"
            3 -> "квітня"
            4 -> "травня"
            5 -> "червня"
            6 -> "липня"
            7 -> "серпня"
            8 -> "вересня"
            9 -> "жовтня"
            10 -> "листопада"
            else -> "грудня"
        }
    }

    fun getCurrentDayOfWar(): Int {

        val myFormat = SimpleDateFormat("dd MM yyyy")
        val startDate = "24 02 2022"

        try {
            val date1: Date = myFormat.parse(startDate)
            val date2 = Date()
            val diff: Long = date2.time - date1.time
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt() + 1
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }
}