package com.example.timerexample.time

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.char

object LocalDateTimeFormats {
    // yyyy/MM/dd
    val yyyy_MM_dd = LocalDateTime.Format {
        year();char('/');monthNumber();char('/');dayOfMonth()
    }

    // HH:mm:ss.SS
    val HH_MM_SS_SS = LocalDateTime.Format {
        hour(); char(':');minute();char(':');second();char('.');secondFraction(2)
    }

    val YYYY_MM_dd_HH_mm = LocalDateTime.Format {
        year();char('/');monthNumber();char('/');dayOfMonth();char(' ');hour();char(':');minute()
    }
}