package com.russialoses.app.feature.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.russialoses.app.MainActivity

class LossesWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        Text(
            text = "slava",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = ColorProvider(
                    color = Color.White
                )
            ),
            modifier = GlanceModifier.clickable(actionStartActivity<MainActivity>())
        )
    }
}

class LossesWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = LossesWidget()

}