package com.pixel.newsapp

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object FragmentExtensions {

    fun formatDurationFromNow(dateString: String): String {
        val dateFormat = when {
            dateString.contains(".") -> SimpleDateFormat(
                Constants.DATE_PATTERN1,
                Locale.getDefault(),
            )

            else -> SimpleDateFormat(Constants.DATE_PATTERN2, Locale.getDefault())
        }
        dateFormat.timeZone = TimeZone.getTimeZone(Constants.TIMEZONE)

        val currentDate = Calendar.getInstance().time
        val providedDate = dateFormat.parse(dateString)
        val difference = currentDate.time - providedDate!!.time

        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 30
        val years = months / 12

        return when {
            years > 0 -> "$years years ago"
            months > 0 -> "$months months ago"
            days > 0 -> "$days days ago"
            hours > 0 -> "$hours hours ago"
            minutes > 0 -> "$minutes minutes ago"
            else -> "$seconds seconds ago"
        }
    }
}
