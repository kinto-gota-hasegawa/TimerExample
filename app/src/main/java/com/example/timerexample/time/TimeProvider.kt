package com.example.timerexample.time

import kotlinx.datetime.LocalDateTime

/**
 * 時刻を提供するインターフェース
 */
interface TimeProvider {
    fun getCurrentDateTime(): LocalDateTime
}