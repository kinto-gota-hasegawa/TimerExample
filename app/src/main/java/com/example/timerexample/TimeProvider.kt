package com.example.timerexample

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

/**
 * 時刻を提供するインターフェース
 * テスト時はFake実装を使用することで、時刻をコントロール可能にする
 */
interface TimeProvider {
    /**
     * 1秒ごとに現在時刻を流すFlow
     */
    fun currentTimeFlow(): Flow<Instant>
}