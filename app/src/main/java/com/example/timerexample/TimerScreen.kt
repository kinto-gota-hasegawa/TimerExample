package com.example.timerexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timerexample.ui.theme.TimerExampleTheme
import kotlinx.datetime.LocalDateTime

/**
 * タイマー画面
 * 日付、時刻、カウントダウンを表示する
 */
@Composable
fun TimerScreen(
    timeProvider: TimeProvider,
    modifier: Modifier = Modifier
) {
    val timeState = rememberTimeState(timeProvider)

    // 初期化
    LaunchedEffect(timeState) {
        timeState.initialize()
    }

    // タイマー開始
    LaunchedEffect(timeState) {
        timeState.startTimer(interval = 10) // 10ms (1/100秒)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = timeState.currentDate,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = timeState.currentTime,
            style = MaterialTheme.typography.displayLarge,
            fontFamily = FontFamily.Monospace
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = timeState.remainingTimeMessage,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    val fakeProvider = FakeTimeProvider(
        currentDateTime = LocalDateTime(2025, 10, 31, 12, 30, 45),
        targetTime = TargetTime(
            targetDateTime = LocalDateTime(2025, 10, 31, 23, 0, 56),
            messageFormat = "あと %1\$s 有効"
        )
    )

    TimerExampleTheme {
        TimerScreen(timeProvider = fakeProvider)
    }
}
