package com.example.timerexample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timerexample.ui.theme.TimerExampleTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * タイマー画面
 * 現在時刻を1秒ごとに更新して表示する
 */
@Composable
fun TimerScreen(
    uiState: TimeUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val localDateTime = uiState.currentTime.toLocalDateTime(TimeZone.currentSystemDefault())
        val timeString = String.format(
            "%02d:%02d:%02d",
            localDateTime.hour,
            localDateTime.minute,
            localDateTime.second
        )

        Text(
            text = timeString,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    TimerExampleTheme {
        TimerScreen(
            uiState = TimeUiState(
                currentTime = Instant.fromEpochMilliseconds(1640995200000) // 2022-01-01 00:00:00 UTC
            )
        )
    }
}
