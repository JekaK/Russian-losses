package com.krykun.data.mappers

import com.krykun.domain.model.RussianLossesItem
import com.krykun.data.model.RussianLossesDataItem

fun RussianLossesDataItem.toRussianLossesItem(): RussianLossesItem {
    return RussianLossesItem(
        aircrafts = this.aircrafts,
        antiAir = this.antiAir,
        armoredVehicles = this.armoredVehicles,
        artillery = this.artillery,
        cisterns = this.cisterns,
        cruiseMissiles = this.cruiseMissiles,
        date = this.date,
        helicopters = this.helicopters,
        mlrs = this.mlrs,
        personnel = this.personnel,
        specialVehicle = this.specialVehicle,
        srmb = this.srmb,
        tanks = this.tanks,
        uav = this.uav,
        vehicles = this.vehicles,
        vessels = this.vessels
    )
}