package io.github.k_ishigaki.mezamashi.model

fun Date.getNext(dayOfWeek: DayOfWeek) = forward(Interval.day(getDayOfWeek().remainsTo(dayOfWeek)))
fun Date.getPrevious(dayOfWeek: DayOfWeek) = backward(Interval.day(getDayOfWeek().passedFrom(dayOfWeek)))

