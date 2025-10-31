package com.example.timerexample

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

    override fun getTimeZone(): TimeZone {
        return TimeZone.currentSystemDefault()
    }

    override suspend fun getTargetDateTime(): TargetTime {
        // 仮想的なAPI応答
        // 実際のアプリではここでAPIコールを行う
        val targetDateTime = LocalDateTime.parse("2025-10-31T23:00:56")
        return TargetTime(
            targetDateTime = targetDateTime,
            messageFormat = "あと %1\$s 有効"
        )
    }
}
