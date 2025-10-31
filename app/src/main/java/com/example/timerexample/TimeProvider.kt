package com.example.timerexample

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

/**
 * 時刻を提供するインターフェース
 * テスト時はFake実装を使用することで、時刻をコントロール可能にする
 */
interface TimeProvider {
    /**
     * 現在時刻を取得
     */
    fun getCurrentDateTime(): LocalDateTime

    /**
     * タイムゾーンを取得
     */
    fun getTimeZone(): TimeZone

    /**
     * APIから取得した目標時刻を取得
     * 実際はAPIコールだが、今回は固定値を返す
     */
    suspend fun getTargetDateTime(): TargetTime
}

/**
 * 目標時刻の情報
 */
data class TargetTime(
    val targetDateTime: LocalDateTime,
    val messageFormat: String // "あと %1\$s 有効"
)