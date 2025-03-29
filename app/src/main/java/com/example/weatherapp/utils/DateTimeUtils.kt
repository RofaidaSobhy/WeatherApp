package com.example.weatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    // Function to get the date
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(unixTimestamp: Int, offsetSeconds: Int): String {
        val utcInstant = Instant.ofEpochSecond(unixTimestamp.toLong()) // Convert to UTC time
        val zoneOffset = ZoneOffset.ofTotalSeconds(offsetSeconds) // Create ZoneOffset
        val localDate = utcInstant.atOffset(zoneOffset) // Apply timezone offset
        return localDate.format(DateTimeFormatter.ofPattern("d MMMM, EEEE")) // Format as day/month/dayName
    }

    // Function to get the time
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTime(unixTimestamp: Int, offsetSeconds: Int): String {
        val utcInstant = Instant.ofEpochSecond(unixTimestamp.toLong()) // Convert to UTC time
        val zoneOffset = ZoneOffset.ofTotalSeconds(offsetSeconds) // Create ZoneOffset
        val localTime = utcInstant.atOffset(zoneOffset) // Apply timezone offset
        return localTime.format(DateTimeFormatter.ofPattern("hh:mm a")) // Format as hours:minutes AM/PM
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDtTxtToHour(dtTxt: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dtTxt, formatter)
        return localDateTime.format(DateTimeFormatter.ofPattern("h a"))
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDtTxtToDayMonth(dtTxt: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dtTxt, formatter)
        return localDateTime.format(DateTimeFormatter.ofPattern("d MMMM"))
    }
}