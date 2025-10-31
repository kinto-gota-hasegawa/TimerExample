package com.example.timerexample.time

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * 本番用の時刻プロバイダー
 * 実際のシステム時刻を使用する
 */
class DefaultTimeProvider : TimeProvider {
    override fun getCurrentDateTime(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }
}
