package com.example.timerexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * TimerViewModelのファクトリー
 * TimeProviderを注入するために使用
 */
class TimerViewModelFactory(
    private val timeProvider: TimeProvider
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return TimerViewModel(timeProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
