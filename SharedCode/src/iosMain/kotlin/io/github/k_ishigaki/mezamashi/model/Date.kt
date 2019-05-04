package io.github.k_ishigaki.mezamashi.model

import platform.Foundation.*

actual class Date(date: NSDate) {
    private val calendar: NSCalendar = NSCalendar(NSCalendarIdentifierGregorian)
    private val components: NSDateComponents = calendar.components(
        NSCalendarUnitYear
                or NSCalendarUnitMonth
                or NSCalendarUnitDay
                or NSCalendarUnitHour
                or NSCalendarUnitMinute
                or NSCalendarUnitSecond
                or NSCalendarUnitWeekday,
        date)

    actual companion object {
        actual fun now() = Date(NSDate())
    }

    actual fun getYear() = components.year.toInt()
    actual fun getMonth() = components.month.toInt()
    actual fun getDay() = components.day.toInt()
    actual fun getHour() = components.hour.toInt()
    actual fun getMinute() = components.minute.toInt()
    actual fun getSecond() = components.second.toInt()
    actual fun forward(interval: Interval) = Date(
        NSDate(components.date!!.timeIntervalSince1970
                + interval.second.toDouble()))
    actual fun backward(interval: Interval) = Date(
        NSDate(components.date!!.timeIntervalSince1970
                - interval.second.toDouble()))
    actual fun getDayOfWeek() = DayOfWeek.fromInt(components.weekday.toInt() - 1)
}
