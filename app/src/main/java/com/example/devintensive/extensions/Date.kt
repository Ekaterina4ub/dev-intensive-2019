package com.example.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat (pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time +=when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time
    return this
}

val Int.sec get() = this * SECOND
val Int.min get() = this * MINUTE
val Int.hour get() = this * HOUR
val Long.day get() = this * DAY

val Long.asMin get() = this.absoluteValue / MINUTE
val Long.asHour get() = this.absoluteValue / HOUR
val Long.asDay get() = this.absoluteValue / DAY

fun Date.humanizeDiff(date: Date = Date()) =
    when(val dateDiff: Long = date.time - time) {
        in 0..1.sec -> "только что"
        in 1.sec..45.sec -> "несколько секунд назад"
        in 45.sec..75.sec -> "минуту назад"
        in 75.sec..45.min -> "${TimeUnits.MINUTE.plural(dateDiff.asMin)} назад"
        in 45.min..75.min-> "час назад"
        in 75.min .. 22.hour -> "${TimeUnits.HOUR.plural(dateDiff.asHour)} назад"
        in 22.hour .. 26.hour -> "день назад"
        in 27.hour..360L.day -> "${TimeUnits.DAY.plural(dateDiff.asDay)} назад"
        in (-1).sec..0.sec -> "только что"
        in (-45).sec..(-1).sec -> "через несколько секунд"
        in (-75).sec..(-45).sec -> "через минуту"
        in (-45).min..(-75).sec -> "через ${TimeUnits.MINUTE.plural(dateDiff.asMin)}"
        in (-75).min..(-45).min -> "через час"
        in (-22).hour..(-75).min -> "через ${TimeUnits.HOUR.plural(dateDiff.asHour)}"
        in (-26).hour..(-22).hour -> "через день"
        in (-360L).day..(-26).hour -> "через ${TimeUnits.DAY.plural(dateDiff.asDay)}"
        else -> if (dateDiff > 0) "более года назад" else "более чем через год"
    }

enum class TimeUnits (val nameOfUnit: Array<String>) {
    SECOND(arrayOf("секунд", "секунду", "секунды")),
    MINUTE(arrayOf("минут", "минуту", "минуты")),
    HOUR(arrayOf("часов", "час", "часа")),
    DAY(arrayOf("дней", "день", "дня"));

    fun plural(value: Long): String = "$value ${this.nameOfUnit[chooseEnding(value)]}"

    private fun chooseEnding(value: Long) = when {
        value % 100 in 5..20 -> 0
        value % 10 in 2..4 -> 2
        value % 10 == 1L -> 1
        else -> 0
    }
}