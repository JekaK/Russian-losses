package com.russialoses.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RussianLossesItem(
    val aircrafts: Int? = null,
    val antiAir: Int? = null,
    val armoredVehicles: Int? = null,
    val artillery: Int? = null,
    val cisterns: Int? = null,
    val cruiseMissiles: Int? = null,
    val date: String? = null,
    val helicopters: Int? = null,
    val mlrs: Int? = null,
    val personnel: Int? = null,
    val specialVehicle: Int? = null,
    val srmb: Int? = null,
    val tanks: Int? = null,
    val uav: Int? = null,
    val vehicles: Int? = null,
    val vessels: Int? = null
) : Parcelable