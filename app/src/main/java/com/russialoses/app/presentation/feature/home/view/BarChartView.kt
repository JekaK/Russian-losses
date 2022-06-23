package com.russialoses.app.presentation.feature.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russialoses.app.R
import com.russialoses.app.presentation.custom.pie.PieChart
import com.russialoses.app.presentation.custom.pie.PieChartData
import com.russialoses.app.presentation.custom.animation.simpleChartAnimation
import com.russialoses.app.presentation.custom.pie.renderer.SimpleSliceDrawer
import com.russialoses.app.domain.model.RussianLossesItem


@Composable
fun BarChartView(russianLosses: List<RussianLossesItem>) {
    val names = listOf(
        stringResource(R.string.tanks),
        stringResource(R.string.bbm),
        stringResource(R.string.aircrafts),
        stringResource(R.string.heli)
    )

    if (russianLosses.isNotEmpty()) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            BarChartRoot(
                current = russianLosses.first().tanks ?: 0,
                prev = russianLosses[1].tanks ?: 0,
                text = names[0]
            )
            BarChartRoot(
                current = russianLosses.first().armoredVehicles ?: 0,
                prev = russianLosses[1].armoredVehicles ?: 0,
                text = names[1]
            )
            BarChartRoot(
                current = russianLosses.first().aircrafts ?: 0,
                prev = russianLosses[1].aircrafts ?: 0,
                text = names[2]
            )
            BarChartRoot(
                current = russianLosses.first().helicopters ?: 0,
                prev = russianLosses[1].helicopters ?: 0,
                text = names[3]
            )
        }
    }
}

@Composable
private fun BarChartRoot(current: Int, prev: Int, text: String) {

    val difference = current.toFloat() - prev
    val configuration = LocalConfiguration.current
    val screenWidthBy5 = configuration.screenWidthDp.dp / 5

    val aircraftSlice = listOf(
        PieChartData.Slice(
            current.toFloat(),
            colorResource(id = R.color.blue_flag) //445fd9
        ),
        PieChartData.Slice(
            difference,
            colorResource(id = R.color.yelow_flag) //f3da3a
        )
    )
    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.padding(top = 20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                PieChart(
                    pieChartData = PieChartData(aircraftSlice),
                    modifier = Modifier
                        .size(screenWidthBy5)
                        .padding(8.dp),
                    animation = simpleChartAnimation(),
                    sliceDrawer = SimpleSliceDrawer(sliceThickness = 10f)
                )
                Text(
                    text = current.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = text,
                modifier = Modifier.padding(top = 8.dp),
                color = colorResource(id = R.color.secondary_text),
                fontSize = 16.sp
            )
        }

        Text(
            text = "+${difference.toInt()}",
            modifier = Modifier.alpha(if (difference.toInt() > 0) 1f else 0f),
            fontSize = 12.sp,
            maxLines = 1,//b9c2c5
            color = colorResource(id = R.color.secondary_text)
        )
    }
}