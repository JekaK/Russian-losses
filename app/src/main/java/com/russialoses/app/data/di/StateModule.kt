package com.russialoses.app.data.di

import com.russialoses.app.state.AppState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StateModule {

    @Singleton
    @Provides
    fun provideAppState() = MutableStateFlow(AppState())
}