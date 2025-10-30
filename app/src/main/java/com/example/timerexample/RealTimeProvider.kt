package com.example.timerexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.seconds

/**
 * 本番用の時刻プロバイダー
 * 実際のシステム時刻を使用する
 */
class RealTimeProvider : TimeProvider {
    override fun currentTimeFlow(): Flow<Instant> = flow {
        while (true) {
            emit(Clock.System.now())
            delay(1.seconds)
        }
    }
}
