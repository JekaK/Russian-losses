package com.russialoses.app.presentation.feature.widget.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.russialoses.app.R
import com.russialoses.app.domain.model.RussianLossesItem

@Composable
fun AdditionalInfoView(russianLosses: List<RussianLossesItem>, modifier: GlanceModifier) {
    val names = listOf(
        LocalContext.current.getString(R.string.tanks),
        LocalContext.current.getString(R.string.bbm),
        LocalContext.current.getString(R.string.aircrafts),
        LocalContext.current.getString(R.string.heli)
    )
    Column(modifier = modifier) {
        BaseInfoView(
            name = names[0],
            count = russianLosses.first().tanks ?: 0,
            difference = (russianLosses.first().tanks ?: 0) - (russianLosses[1].tanks ?: 0)
        )
        BaseInfoView(
            name = names[1],
            count = russianLosses.first().armoredVehicles ?: 0,
            difference = (russianLosses.first().armoredVehicles
                ?: 0) - (russianLosses[1].armoredVehicles ?: 0)
        )
        BaseInfoView(
            name = names[2],
            count = russianLosses.first().aircrafts ?: 0,
            difference = (russianLosses.first().aircrafts ?: 0) - (russianLosses[1].tanks ?: 0)
        )
        BaseInfoView(
            name = names[3],
            count = russianLosses.first().helicopters ?: 0,
            difference = (russianLosses.first().helicopters ?: 0) - (russianLosses[1].helicopters
                ?: 0),
            needDelimiter = false
        )
    }
}

@Composable
fun BaseInfoView(
    name: String,
    count: Int,
    difference: Int,
    modifier: GlanceModifier = GlanceModifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp, end = 16.dp),
    needDelimiter: Boolean = true
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.Top, modifier = GlanceModifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = name,
                style = TextStyle(
                    color = ColorProvider(R.color.secondary_text),
                    fontSize = 16.sp,
                ),
                maxLines = 1,
            )

            Spacer(modifier = GlanceModifier.defaultWeight())

            Text(
                text = count.toString(),
                style = TextStyle(
                    color = ColorProvider(
                        Color.White,
                    ),
                    fontSize = 20.sp,
                ),
                maxLines = 1,
            )
            Column(verticalAlignment = Alignment.Top) {
                Text(
                    text = if (difference > 0) {
                        "+$difference"
                    } else {
                        ""
                    },
                    style = TextStyle(
                        color = ColorProvider(R.color.secondary_text),
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                )
            }
        }
        if (needDelimiter) {
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        ColorProvider(R.color.secondary_text)
                    )
            ) {}
        }
    }
}

