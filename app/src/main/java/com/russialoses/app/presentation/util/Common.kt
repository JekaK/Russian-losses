package com.russialoses.app.presentation.util

import android.text.format.Time
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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