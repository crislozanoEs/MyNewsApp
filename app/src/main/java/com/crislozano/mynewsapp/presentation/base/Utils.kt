package com.crislozano.mynewsapp.presentation.base

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.toPresentationDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)

        if (date != null) {
            outputFormat.format(date)
        } else {
            "Date"
        }
    } catch (e: Exception) {
        "Date"
    }
}