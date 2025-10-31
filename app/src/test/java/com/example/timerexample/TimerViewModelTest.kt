package com.example.timerexample

import com.example.timerexample.time.FakeTimeProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TimerViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeTimeProvider: FakeTimeProvider
    private lateinit var viewModel: TimerViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeTimeProvider = com.example.timerexample.time.FakeTimeProvider()
        viewModel = TimerViewModel(fakeTimeProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `初期状態では時刻が0`() = runTest {
        // Given
        advanceUntilIdle()

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(Instant.fromEpochMilliseconds(0), uiState.currentTime)
    }

    @Test
    fun `TimeProviderから時刻が更新される`() = runTest {
        // Given
        val testTime = Instant.fromEpochMilliseconds(1640995200000) // 2022-01-01 00:00:00 UTC
        advanceUntilIdle()

        // When
        fakeTimeProvider.setCurrentTime(testTime)
        advanceUntilIdle()

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(testTime, uiState.currentTime)
    }

    @Test
    fun `TimeProviderから複数回時刻が更新される`() = runTest {
        // Given
        advanceUntilIdle()

        // When & Then
        val testTime1 = Instant.fromEpochMilliseconds(1640995200000)
        fakeTimeProvider.setCurrentTime(testTime1)
        advanceUntilIdle()
        assertEquals(testTime1, viewModel.uiState.value.currentTime)

        val testTime2 = Instant.fromEpochMilliseconds(1640995201000)
        fakeTimeProvider.setCurrentTime(testTime2)
        advanceUntilIdle()
        assertEquals(testTime2, viewModel.uiState.value.currentTime)

        val testTime3 = Instant.fromEpochMilliseconds(1640995202000)
        fakeTimeProvider.setCurrentTime(testTime3)
        advanceUntilIdle()
        assertEquals(testTime3, viewModel.uiState.value.currentTime)
    }
}
