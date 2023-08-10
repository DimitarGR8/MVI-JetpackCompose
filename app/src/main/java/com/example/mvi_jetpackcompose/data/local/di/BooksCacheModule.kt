package com.example.mvi_jetpackcompose.data.local.di

import com.example.mvi_jetpackcompose.data.local.cache.FavoriteBooksCache
import com.example.mvi_jetpackcompose.data.local.cache.IFavoriteBooksCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BooksCacheModule {

    @Binds
    @Singleton
    abstract fun bindFavouriteBooksCache(booksCache: FavoriteBooksCache): IFavoriteBooksCache
}
