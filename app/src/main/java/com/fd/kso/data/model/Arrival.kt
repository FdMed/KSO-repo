package com.fd.kso.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Arrival(val place : String, val datetime: String, val coord: Coordinate) : Parcelable
