package com.example.greenplate.donationScreen

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

data class Donation(
    val id: String = "", // Unique document ID from Firestore
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val timestamp: Long = 0L
) {
    val daysLeft: String
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            val donationDate = Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val today = LocalDate.now()

            return when (val daysBetween = ChronoUnit.DAYS.between(donationDate, today).toInt()) {
                0 -> "Today"
                1 -> "1 day ago"
                2 -> "2 days ago"
                in 3..6 -> "$daysBetween days ago"
                else -> "$daysBetween days ago"
            }

        }
}
