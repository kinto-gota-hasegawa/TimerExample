package com.example.timerexample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toInstant
import kotlin.time.Duration

@Composable
fun rememberTimeState(
    timeProvider: TimeProvider,
): TimeState {
    return remember(timeProvider) {
        TimeState(timeProvider)
    }
}

class TimeState(
    private val timeProvider: TimeProvider
) {
    var currentDate by mutableStateOf("")
        private set

    var currentTime by mutableStateOf("")
        private set

    var remainingTimeMessage by mutableStateOf("")
        private set

    private var targetTime: TargetTime? = null

    suspend fun initialize() {
        targetTime = timeProvider.getTargetDateTime()
    }

    suspend fun startTimer(interval: Long = 10) {
        while (true) {
            updateTime()
            delay(interval)
        }
    }

    private fun updateTime() {
        val now = timeProvider.getCurrentDateTime()
        currentDate = formatDate(now)
        currentTime = formatTime(now)

        targetTime?.let { target ->
            val duration = calculateDuration(now, target.targetDateTime)
            val formattedDuration = formatDuration(duration)
            remainingTimeMessage = target.messageFormat.format(formattedDuration)
        }
    }

    private fun formatDate(dateTime: LocalDateTime): String {
        return "${dateTime.year}/${dateTime.monthNumber.toString().padStart(2, '0')}/${dateTime.dayOfMonth.toString().padStart(2, '0')}"
    }

    private fun formatTime(dateTime: LocalDateTime): String {
        val centisecond = (dateTime.nanosecond / 10_000_000).toString().padStart(2, '0')
        return "${dateTime.hour.toString().padStart(2, '0')}:${dateTime.minute.toString().padStart(2, '0')}:${dateTime.second.toString().padStart(2, '0')}.${centisecond}"
    }

    private fun calculateDuration(now: LocalDateTime, target: LocalDateTime): Duration {
        val duration = target.toInstant(timeProvider.getTimeZone()) -
                      now.toInstant(timeProvider.getTimeZone())
        return if (duration.isNegative()) Duration.ZERO else duration
    }

    private fun formatDuration(duration: Duration): String {
        val hours = duration.inWholeHours
        val minutes = (duration.inWholeMinutes % 60)
        val seconds = (duration.inWholeSeconds % 60)

        return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    }
}
