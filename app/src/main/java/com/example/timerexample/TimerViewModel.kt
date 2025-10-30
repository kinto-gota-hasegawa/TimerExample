package com.example.timerexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * タイマー画面のViewModel
 */
class TimerViewModel(
    private val timeProvider: TimeProvider
) : ViewModel() {

    /**
     * UI State
     * TimeProviderから1秒ごとに更新される現在時刻を保持
     */
    val uiState: StateFlow<TimeUiState> = timeProvider
        .currentTimeFlow()
        .map { currentTime ->
            TimeUiState(currentTime = currentTime)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TimeUiState()
        )
}
