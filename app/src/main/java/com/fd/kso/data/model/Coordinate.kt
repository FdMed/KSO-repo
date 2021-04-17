package com.fd.kso.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinate(val lat: Double, val lon: Double) : Parcelable
