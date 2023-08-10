package com.example.mvi_jetpackcompose.data.repository.di

import com.example.mvi_jetpackcompose.data.repository.BookDetailsRepository
import com.example.mvi_jetpackcompose.data.repository.BooksRepository
import com.example.mvi_jetpackcompose.domain.repository.IBookDetailsRepository
import com.example.mvi_jetpackcompose.domain.repository.IBooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BooksRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBooksRepository(repo: BooksRepository): IBooksRepository

    @Binds
    @Singleton
    abstract fun bindBooksDetailsRepository(repo: BookDetailsRepository): IBookDetailsRepository
}
