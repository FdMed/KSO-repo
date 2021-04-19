package com.fd.kso.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyItem(val id: String, val departure: Departure, val arrival: Arrival, val details: Detail) : Parcelable