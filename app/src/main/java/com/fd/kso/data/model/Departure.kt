package com.fd.kso.data.model

import android.os.Parcelable
import com.fd.kso.utils.DateUtils
import kotlinx.parcelize.Parcelize

@Parcelize
data class Departure(val place : String, val datetime: String, val coord: Coordinate?) : Parcelable {
    fun getFormattedDate():String = DateUtils.formatDateString(datetime)
    fun getFormattedTime():String = DateUtils.formatDateTimeString(datetime)

}
