package com.fd.kso.utils

import android.annotation.SuppressLint
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    const val DEFAULT_FORMAT_DATE_WITHOUT_TIME = "dd-MM-yyyy"
    const val DEFAULT_FORMAT_TIME_WITHOUT_DATE = "HH:mm"
    const val DEFAULT_FORMAT_DATE = "yyyyMMdd'T'HHmmss"


    @SuppressLint("SimpleDateFormat")
    fun formatDateString(timeStamp: String) : String {
        val x = SimpleDateFormat(DEFAULT_FORMAT_DATE)
        val d = x.parse(timeStamp)
        return formatDate(d,DEFAULT_FORMAT_DATE_WITHOUT_TIME)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDateTimeString(timeStamp: String) : String {
        val x = SimpleDateFormat(DEFAULT_FORMAT_DATE)
        val d = x.parse(timeStamp)
        return formatDate(d,DEFAULT_FORMAT_TIME_WITHOUT_DATE)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: Date?, formatStr: String?): String {
        return SimpleDateFormat(formatStr ?: DEFAULT_FORMAT_DATE).format(
                date!!
        )
    }

    fun secondsTominutes(duration: Int) : String {
       var converted =  (duration / 60)%60
        return  converted.toString()
    }
}