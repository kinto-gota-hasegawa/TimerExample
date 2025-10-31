package com.example.timerexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.timerexample.time.DefaultTimeProvider
import com.example.timerexample.time.TimeProvider
import com.example.timerexample.ui.theme.TimerExampleTheme
import kotlinx.datetime.LocalDateTime

class MainActivity : ComponentActivity() {

    private val timeProvider: TimeProvider = DefaultTimeProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TicketScreen(
                        uiState = TicketUiState(
                            targetDateTime = LocalDateTime(2025, 10, 31, 23, 0, 56),
                            messageFormat = "あと %1\$s 有効"
                        ),
                        timeProvider = timeProvider,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}