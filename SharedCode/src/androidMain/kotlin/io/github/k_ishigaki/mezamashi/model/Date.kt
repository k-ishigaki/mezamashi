package io.github.k_ishigaki.mezamashi.model

import java.util.*
import java.util.Calendar.*

internal fun createCalendarInstance(): Calendar {
    // java.util.Calendar.getInstance is NOT singleton
    return getInstance()
}

actual class Date(date: java.util.Date) {
    private val calendar: Calendar = createCalendarInstance().apply {
        time = date
    }

    actual companion object {
        actual fun now() = Date(Date())
    }

    actual fun getYear() = calendar[YEAR]
    actual fun getMonth() = calendar[MONTH]
    actual fun getDay() = calendar[DAY_OF_MONTH]
    actual fun getHour() = calendar[HOUR_OF_DAY]
    actual fun getMinute() = calendar[MINUTE]
    actual fun getSecond() = calendar[SECOND]
    actual fun forward(interval: Interval) = Date(Date(calendar.timeInMillis + interval.second * 1000L))
    actual fun backward(interval: Interval) = Date(Date(calendar.timeInMillis + interval.second * 1000L))
    actual fun getDayOfWeek() = DayOfWeek.fromInt(calendar[DAY_OF_WEEK] - 1)
}
