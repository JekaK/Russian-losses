package com.russialoses.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RussianLossesDataItem(
    @SerializedName("aircrafts")
    val aircrafts: Int? = null,
    @SerializedName("antiAir")
    val antiAir: Int? = null,
    @SerializedName("armoredVehicles")
    val armoredVehicles: Int? = null,
    @SerializedName("artillery")
    val artillery: Int? = null,
    @SerializedName("cisterns")
    val cisterns: Int? = null,
    @SerializedName("cruiseMissiles")
    val cruiseMissiles: Int? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("helicopters")
    val helicopters: Int? = null,
    @SerializedName("mlrs")
    val mlrs: Int? = null,
    @SerializedName("personnel")
    val personnel: Int? = null,
    @SerializedName("specialVehicle")
    val specialVehicle: Int? = null,
    @SerializedName("srmb")
    val srmb: Int? = null,
    @SerializedName("tanks")
    val tanks: Int? = null,
    @SerializedName("uav")
    val uav: Int? = null,
    @SerializedName("vehicles")
    val vehicles: Int? = null,
    @SerializedName("vessels")
    val vessels: Int? = null
) : Parcelable