package com.monty.jetgooglerepository.android.presentation.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateTimeUtil {

    /**
     * This function will parse the given date to a specific format
     *
     * @param date: String
     * @return String date with pattern EEEE dd, MMMM yyyy, ex: Monday 25, May 2024
     */
    fun parseRepositoryDate(date: String): String {
        return try {
            val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
            val zonedDateTime = ZonedDateTime.parse(date, inputFormatter)
            val outputFormatter = DateTimeFormatter.ofPattern("EEEE dd, MMMM yyyy", Locale.ENGLISH)
            zonedDateTime.format(outputFormatter)
        } catch (e: Exception) {
            "Invalid date"
        }
    }
}