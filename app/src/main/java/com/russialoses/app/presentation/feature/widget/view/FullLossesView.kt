package com.russialoses.app.presentation.feature.widget.view

import android.text.format.Time
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.russialoses.app.R
import com.russialoses.app.presentation.util.DateMapper

@Composable
fun FullLossesView(
    current: Int,
    previous: Int,
    modifier: GlanceModifier,
    shouldUseTime: Boolean = true
) {
    val day = if (shouldUseTime) {
        val today = Time(Time.getCurrentTimezone())
        today.setToNow()
        today.monthDay
    } else {
        0
    }

    Column(
        modifier = modifier.padding(
            top = 20.dp,
            start = 20.dp,
            end = 20.dp,
            bottom = 20.dp
        )
    ) {
        Text(
            text = "На $day ${DateMapper.getCurrentUkrainianMonth()}",
        )
        Box(
            modifier = GlanceModifier.padding(top = 20.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Row {
                Text(
                    text = "≈ $current",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = ColorProvider(Color.White)
                    )
                )
                Column(verticalAlignment = Alignment.Top) {
                    Text(
                        text = "+${current - previous}",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = ColorProvider(R.color.secondary_text),
                        )
                    )
                }
            }
        }
    }
}