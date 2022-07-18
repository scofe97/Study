package com.example.ch4_3.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationLatLngEntity(
    val latitude : Float,
    val longitude : Float
) : Parcelable {
}