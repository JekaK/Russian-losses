package com.russialoses.app.presentation.feature.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.work.*
import com.google.gson.Gson
import com.krykun.domain.model.RussianLossesItem
import com.russialoses.app.MainActivity
import com.russialoses.app.R
import com.russialoses.app.presentation.feature.widget.view.AdditionalInfoView
import com.russialoses.app.presentation.feature.widget.view.FullLossesView
import com.russialoses.app.presentation.util.fromJson
import com.russialoses.app.presentation.work.UpdateLossesDataWork
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class LossesWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        val prefs = currentState<Preferences>()
        val fullData =
            Gson().fromJson<List<RussianLossesItem>>(prefs[LossesWidgetReceiver.fullData] ?: "")

        fullData?.let {
            Row(
                modifier = GlanceModifier.background(ImageProvider(R.drawable.background_gradient))
                    .fillMaxWidth().clickable(actionStartActivity<MainActivity>())
            ) {
                FullLossesView(
                    current = fullData?.first()?.personnel ?: 0,
                    previous = fullData?.get(1)?.personnel ?: 0,
                    modifier = GlanceModifier
                        .defaultWeight()
                )
                AdditionalInfoView(fullData, modifier = GlanceModifier.defaultWeight())
            }
        }
    }
}

class LossesWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget
        get() = LossesWidget()

    private val coroutineScope = MainScope()

    private var updateWorkRequest: PeriodicWorkRequest? = null

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        if (updateWorkRequest == null) {
            updateWorkRequest = PeriodicWorkRequestBuilder<UpdateLossesDataWork>(
                2,
                TimeUnit.DAYS
            ).setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            ).build()

            WorkManager
                .getInstance(context)
                .enqueue(updateWorkRequest!!)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == UPDATE_ACTION) {
            observeData(context, intent)
        }
    }

    private fun observeData(context: Context, intent: Intent) {
        coroutineScope.launch {
            val glanceId =
                GlanceAppWidgetManager(context).getGlanceIds(LossesWidget::class.java).firstOrNull()

            glanceId?.let {
                updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { pref ->
                    pref.toMutablePreferences().apply {
                        val resultArray = mutableListOf<RussianLossesItem>()
                        (intent.extras?.get(DATA_VALUE) as Array<*>).forEach {
                            resultArray.add(it as RussianLossesItem)
                        }
                        if (intent.extras?.get(DATA_VALUE) != null) {
                            this[fullData] = Gson().toJson(resultArray)
                        }
                    }
                }
                glanceAppWidget.update(context, it)
            }

        }
    }

    companion object {
        const val DATA_VALUE = "dataValue"
        const val UPDATE_ACTION = "updateAction"
        val fullData = stringPreferencesKey("fullData")
    }
}