package com.example.timerexample

import kotlinx.datetime.Instant

/**
 * タイマー画面のUIステート
 */
data class TimeUiState(
    val currentTime: Instant = Instant.fromEpochMilliseconds(0)
)
