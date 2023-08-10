package com.example.mvi_jetpackcompose.core.di

import com.example.mvi_jetpackcompose.core.util.events.EventBus
import com.example.mvi_jetpackcompose.core.util.events.IEventBus
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    @Singleton
    fun bindEventBus(eventBus: EventBus): IEventBus

    companion object {
        @Provides
        @Singleton
        fun provideGson() = Gson()

        @Provides
        @Named("io")
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Named("main")
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @Named("default")
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
    }
}
