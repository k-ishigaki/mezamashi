package io.github.k_ishigaki.mezamashi.model

enum class DayOfWeek(private val index: Int) {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6);

    companion object {
        fun fromInt(index: Int) = values().firstOrNull { it.index == index } ?: SUNDAY
    }

    /**
     * Returns number of remaining days to next day of week.
     *
     * @param next next day of week.
     * @return number of remaining days.
     */
    fun remainsTo(next: DayOfWeek): Int {
        val diff = next.index - index
        return if (diff > 0) diff else diff + values().size
    }

    /**
     * Returns number of passed days from previous day of week.
     *
     * @param previous previous day of week.
     * @return number of remaining days.
     */
    fun passedFrom(previous: DayOfWeek): Int {
        val diff = index - previous.index
        return if (diff > 0) diff else diff + values().size
    }
}

expect class Date {
    companion object {
        fun now(): Date
    }
    fun getYear(): Int
    fun getMonth(): Int
    fun getDay(): Int
    fun getHour(): Int
    fun getMinute(): Int
    fun getSecond(): Int
    fun forward(interval: Interval): Date
    fun backward(interval: Interval): Date
    fun getDayOfWeek(): DayOfWeek
}

fun Date.getNext(dayOfWeek: DayOfWeek) = forward(Interval.day(getDayOfWeek().remainsTo(dayOfWeek)))
fun Date.getPrevious(dayOfWeek: DayOfWeek) = backward(Interval.day(getDayOfWeek().passedFrom(dayOfWeek)))

class Interval private constructor(val second: Long) {
    companion object {
        fun second(second: Int) = Interval(second.toLong())
        fun minute(minute: Int) = Interval(60L * minute)
        fun hour(hour: Int)     = Interval(60L * 60L * hour)
        fun day(day: Int)       = Interval(24L * 60L * 60L * day)
    }
}

