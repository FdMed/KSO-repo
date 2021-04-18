package com.fd.kso.data.model

import android.os.Parcelable
import com.fd.kso.utils.DateUtils
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(val duration_second: Int? = null, val distance_m: Double? = null, val co2_emission: Double) : Parcelable {
    fun getFormattedDuration(): String? = duration_second?.let { DateUtils.secondsTominutes(it) }
    fun getDistanceKM():String = (distance_m?.div(1000)).toString().replace("."," km ") + " m"

}
