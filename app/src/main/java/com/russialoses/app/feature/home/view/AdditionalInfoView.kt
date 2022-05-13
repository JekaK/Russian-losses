package com.russialoses.app.feature.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.russialoses.app.R
import com.russialoses.app.model.RussianLossesItem

@Composable
fun AdditionalInfoView(russianLosses: List<RussianLossesItem>) {
    if (russianLosses.isNotEmpty()) {
        CompositionOfViews(
            firstName = stringResource(R.string.artilery),
            secondName = stringResource(R.string.mlrs),
            firstCount = russianLosses.first().artillery ?: 0,
            secondCount = russianLosses.first().mlrs ?: 0,
            firstDifference = (russianLosses.first().artillery ?: 0) -
                    (russianLosses[1].artillery ?: 0),
            secondDifference = (russianLosses.first().mlrs ?: 0) -
                    (russianLosses[1].mlrs ?: 0)
        )

        CompositionOfViews(
            firstName = stringResource(R.string.anti_air),
            secondName = stringResource(R.string.uav),
            firstCount = russianLosses.first().antiAir ?: 0,
            secondCount = russianLosses.first().uav ?: 0,
            firstDifference = (russianLosses.first().antiAir ?: 0) -
                    (russianLosses[1].antiAir ?: 0),
            secondDifference = (russianLosses.first().uav ?: 0) -
                    (russianLosses[1].uav ?: 0)
        )

        CompositionOfViews(
            firstName = stringResource(R.string.cruise_missles),
            secondName = stringResource(R.string.ships),
            firstCount = russianLosses.first().cruiseMissiles ?: 0,
            secondCount = russianLosses.first().vessels ?: 0,
            firstDifference = (russianLosses.first().cruiseMissiles ?: 0) -
                    (russianLosses[1].cruiseMissiles ?: 0),
            secondDifference = (russianLosses.first().vessels ?: 0) -
                    (russianLosses[1].vessels ?: 0)
        )

        CompositionOfViews(
            firstName = stringResource(R.string.vehicle),
            secondName = stringResource(R.string.special_vehicle),
            firstCount = russianLosses.first().vehicles ?: 0,
            secondCount = russianLosses.first().specialVehicle ?: 0,
            firstDifference = (russianLosses.first().vehicles ?: 0) -
                    (russianLosses[1].vehicles ?: 0),
            secondDifference = (russianLosses.first().specialVehicle ?: 0) -
                    (russianLosses[1].specialVehicle ?: 0),
            needDelimiter = false
        )
    }
}

@Composable
fun CompositionOfViews(
    firstName: String,
    secondName: String,
    firstCount: Int,
    secondCount: Int,
    firstDifference: Int,
    secondDifference: Int,
    needDelimiter: Boolean = true
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        val universalModifier = Modifier
            .fillMaxWidth()
            .weight(1f, true)
            .padding(start = 16.dp, end = 16.dp)

        AdditionalInfo(
            name = firstName,
            count = firstCount,
            difference = firstDifference,
            universalModifier,
            needDelimiter
        )

        AdditionalInfo(
            name = secondName,
            count = secondCount,
            difference = secondDifference,
            modifier = universalModifier,
            needDelimiter
        )
    }
}

@Composable
private fun AdditionalInfo(
    name: String,
    count: Int,
    difference: Int,
    modifier: Modifier,
    needDelimiter: Boolean
) {
    ConstraintLayout(modifier = modifier) {
        val (text1, text2, text3, divider) = createRefs()

        Text(
            text = name,
            color = colorResource(id = R.color.secondary_text),
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(parent.top)
                bottom.linkTo(divider.top)
                start.linkTo(parent.start)
                end.linkTo(text2.start)
                width = Dimension.fillToConstraints
            },
        )

        Text(
            text = count.toString(),
            color = Color.White,
            fontSize = 20.sp,
            maxLines = 1,
            modifier = Modifier.constrainAs(text2) {
                top.linkTo(parent.top)
                bottom.linkTo(divider.top)
                end.linkTo(text3.start)
            }
        )
        Text(
            text = if (difference > 0) {
                "+$difference"
            } else {
                ""
            },
            color = colorResource(id = R.color.secondary_text),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = if (difference > 0) 3.dp else 0.dp)
                .constrainAs(text3) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            maxLines = 1,
        )
        if (needDelimiter) {
            Divider(
                color = colorResource(id = R.color.secondary_text),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(divider) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}