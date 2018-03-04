package com.apollo.base.extensions

import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

/**
 * Timestamps = epoch milliseconds UTC
 *
 * Formatting: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */

fun timestampNow(): Long = Instant.now().toEpochMilli()

fun Long.toInstant() = Instant.ofEpochMilli(this)

fun Long.toZonedDateTime() = ZonedDateTime
        .ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())

fun Long.untilNow(): Duration {
    val then = this.toZonedDateTime()
    val now = ZonedDateTime.now()
    return Duration.between(then, now)
}



fun instantNow(): Instant = Instant.now()

fun Instant.toTimestamp(): Long = this.toEpochMilli()

fun Instant.toZonedDateTime() = ZonedDateTime.ofInstant(this, ZoneId.systemDefault())

fun Instant.untilNow(): Duration {
    val then = this.toZonedDateTime()
    val now = ZonedDateTime.now()
    return Duration.between(then, now)
}



fun Duration.toStringShorted(): String {
    var message = ""
    this.toDays().takeIf { it != 0L }.doIfNotNull { message += "${it}d " }
    this.toHours().takeIf { it != 0L }.doIfNotNull { message += "${it}h " }
    this.toMinutes().takeIf { it != 0L }.doIfNotNull { message += "${it}m" }
    return message
}



fun zonedDateTimeNow() : ZonedDateTime = ZonedDateTime.ofInstant(instantNow(), ZoneId.systemDefault())

fun ZonedDateTime.toStringHourAndMinute(): String =
        "${this.hour}:${this.minute}"

fun ZonedDateTime.toStringDateAndTimeWithoutYear(): String =
        this.format(DateTimeFormatter.ofPattern("dd/MM HH:mm", Locale.getDefault()))

fun ZonedDateTime.toStringDateAndTime(): String =
        this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault()))

fun ZonedDateTime.toStringTime(): String =
        this.format(DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()))

fun ZonedDateTime.toStringDate(): String =
        this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()))
