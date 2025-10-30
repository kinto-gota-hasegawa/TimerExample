package com.example.timerexample.di

import com.example.timerexample.RealTimeProvider
import com.example.timerexample.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimerModule {

    @Provides
    @Singleton
    fun provideTimeProvider(): TimeProvider {
        return RealTimeProvider()
    }
}
