package com.example.timerexample

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

/**
 * テスト用の時刻プロバイダー
 * 時刻を手動で設定・変更できる
 */
class FakeTimeProvider(
    private var currentDateTime: LocalDateTime,
    private val targetTime: TargetTime
) : TimeProvider {
    override fun getCurrentDateTime(): LocalDateTime = currentDateTime

    override fun getTimeZone(): TimeZone = TimeZone.UTC

    override suspend fun getTargetDateTime(): TargetTime = targetTime

    /**
     * テスト用: 現在時刻を手動で設定
     */
    fun setCurrentDateTime(dateTime: LocalDateTime) {
        currentDateTime = dateTime
    }
}
