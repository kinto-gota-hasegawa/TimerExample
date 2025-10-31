package com.example.timerexample

import com.example.timerexample.time.TimeProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Instant

/**
 * テスト用の時刻プロバイダー
 * 時刻を外部からコントロール可能
 */
class FakeTimeProvider : TimeProvider {
    private val _currentTime = MutableStateFlow(Instant.fromEpochMilliseconds(0))

    /**
     * 現在時刻を設定する
     */
    fun setCurrentTime(instant: Instant) {
        _currentTime.value = instant
    }
}
