package com.fd.kso.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(val duration_second: Int, val distance_m: Int, val co2_emission: Double) : Parcelable
