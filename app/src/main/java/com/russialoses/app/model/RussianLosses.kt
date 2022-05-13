package com.russialoses.app.model

import com.google.gson.annotations.SerializedName

data class RussianLossesItem(
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
)