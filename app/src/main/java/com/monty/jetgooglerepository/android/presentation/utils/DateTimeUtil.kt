package com.monty.jetgooglerepository.android.presentation.utils

import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateTimeUtil {

    fun parseRepositoryDate(date: String): String {
        return try {
            val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
            val zonedDateTime = ZonedDateTime.parse(date, inputFormatter)
            val localDate = zonedDateTime.toLocalDate()
            val outputFormatter = DateTimeFormatter.ofPattern("EEEE dd, MMMM yyyy", Locale.ENGLISH)
            zonedDateTime.format(outputFormatter)
        } catch (e: Exception) {
            "Invalid date"
        }
    }
}