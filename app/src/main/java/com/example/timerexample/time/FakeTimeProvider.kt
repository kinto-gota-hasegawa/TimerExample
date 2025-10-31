package com.example.timerexample.time

import kotlinx.datetime.LocalDateTime

/**
 * テスト用の時刻プロバイダー
 * 時刻を手動で設定・変更できる
 */
class FakeTimeProvider(
    private var currentDateTime: LocalDateTime,
) : TimeProvider {
    override fun getCurrentDateTime(): LocalDateTime = currentDateTime

    /**
     * テスト用: 現在時刻を手動で設定
     */
    fun setCurrentDateTime(dateTime: LocalDateTime) {
        currentDateTime = dateTime
    }
}
