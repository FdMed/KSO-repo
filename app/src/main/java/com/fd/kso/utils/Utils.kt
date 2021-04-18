package com.fd.kso.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val BASE_URL = "https://run.mocky.io/v3/"
    const val ITEM_BUNDLE_ARG = "ITEM_BUNDLE_KEY_ARG"


    /**
     * Cette methode permet le formattage
     * et la mise en forme de notre liste d'éléments
     * @param sapannableText c'est le text d'entree à spanner
     * @param location prend le nom de position de départ/arrivée
     * @param date prend les valeurs date départ/arrivée
     * @param time prend les valeurs temps départ/arrivée
     * @return un text spanné pret pour l'affichage
     */
    fun spanText(sapannableText : String, location : String, date: String , time: String) : SpannableString {
        val spannable = SpannableString(sapannableText)
        spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                spannable.indexOf(location), spannable.indexOf(location) + location.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                spannable.indexOf(date),  spannable.indexOf(date) + date.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannable.setSpan(
                ForegroundColorSpan(Color.parseColor("#007992")),
                spannable.indexOf(time),  spannable.indexOf(time) + time.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                spannable.indexOf(time),  spannable.indexOf(time) + time.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannable
    }

}