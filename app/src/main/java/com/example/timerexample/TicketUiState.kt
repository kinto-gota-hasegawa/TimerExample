package com.example.timerexample

import kotlinx.datetime.LocalDateTime

data class TicketUiState(
    val targetDateTime: LocalDateTime? = null,
    val messageFormat: String = "あと %1\$s 有効"
)