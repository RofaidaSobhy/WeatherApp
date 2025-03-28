package com.example.weatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    // Function to get the date
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(unixTimestamp: Long, offsetSeconds: Int): String {
        val utcInstant = Instant.ofEpochSecond(unixTimestamp) // Convert to UTC time
        val zoneOffset = ZoneOffset.ofTotalSeconds(offsetSeconds) // Create ZoneOffset
        val localDate = utcInstant.atOffset(zoneOffset) // Apply timezone offset
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) // Format as day/month/year
    }

    // Function to get the time
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTime(unixTimestamp: Long, offsetSeconds: Int): String {
        val utcInstant = Instant.ofEpochSecond(unixTimestamp) // Convert to UTC time
        val zoneOffset = ZoneOffset.ofTotalSeconds(offsetSeconds) // Create ZoneOffset
        val localTime = utcInstant.atOffset(zoneOffset) // Apply timezone offset
        return localTime.format(DateTimeFormatter.ofPattern("hh:mm a")) // Format as hours:minutes AM/PM
    }
}