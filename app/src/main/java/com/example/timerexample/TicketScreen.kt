package com.example.timerexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timerexample.time.FakeTimeProvider
import com.example.timerexample.time.LocalDateTimeFormats
import com.example.timerexample.time.TimeProvider
import com.example.timerexample.ui.theme.TimerExampleTheme
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toInstant

/**
 * タイマー画面
 * 日付、時刻、カウントダウンを表示する
 */
@Composable
fun TicketScreen(
    uiState: TicketUiState,
    timeProvider: TimeProvider,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TodayText(timeProvider)

        Spacer(modifier = Modifier.height(16.dp))

        CurrentTimeText(timeProvider)

        Spacer(modifier = Modifier.height(32.dp))

        RemainingTimeText(uiState, timeProvider)
    }
}

@Composable
private fun TodayText(
    timeProvider: TimeProvider
) {
    var today by remember { mutableStateOf("----/--/--") }
    LaunchedEffect(Unit) {
        while (true) {
            val now = timeProvider.getCurrentDateTime()
            today = now.format(LocalDateTimeFormats.yyyy_MM_dd)
            delay(1_000)
        }
    }
    Text(
        text = today,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
private fun CurrentTimeText(
    timeProvider: TimeProvider
) {
    var currentTime by remember { mutableStateOf("--:--:--.--") }
    LaunchedEffect(Unit) {
        while (true) {
            val now = timeProvider.getCurrentDateTime()
            currentTime = now.format(LocalDateTimeFormats.HH_MM_SS_SS)
            delay(10)
        }
    }
    Text(
        text = currentTime,
        style = MaterialTheme.typography.displayMedium,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
private fun RemainingTimeText(
    uiState: TicketUiState,
    timeProvider: TimeProvider
) {
    val targetDateTime = uiState.targetDateTime
    val messageFormat = uiState.messageFormat

    var remainingMessage by remember { mutableStateOf("") }

    LaunchedEffect(targetDateTime) {
        while (true) {
            if (targetDateTime == null) {
                remainingMessage = ""
            } else {
                val now = timeProvider.getCurrentDateTime()
                val duration = targetDateTime.toInstant(TimeZone.UTC) - now.toInstant(TimeZone.UTC)
                if (duration.isNegative()) {
                    remainingMessage = messageFormat.format("00:00:00")
                } else {
                    val hours = duration.inWholeHours
                    val minutes = (duration.inWholeMinutes % 60)
                    val seconds = (duration.inWholeSeconds % 60)
                    val formatted = "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
                    remainingMessage = messageFormat.format(formatted)
                }
            }
            delay(1_000)
        }
    }

    Text(
        text = remainingMessage,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Preview(showBackground = true)
@Composable
fun TicketScreenPreview() {
    val fakeProvider = FakeTimeProvider(
        currentDateTime = LocalDateTime(2025, 10, 31, 12, 30, 45),
    )

    val uiState = TicketUiState(
        targetDateTime = LocalDateTime(2025, 10, 31, 23, 0, 56),
        messageFormat = "あと %1\$s 有効"
    )

    TimerExampleTheme {
        TicketScreen(
            uiState = uiState,
            timeProvider = fakeProvider
        )
    }
}
