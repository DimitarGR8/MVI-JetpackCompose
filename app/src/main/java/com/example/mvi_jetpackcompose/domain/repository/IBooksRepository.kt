package com.example.mvi_jetpackcompose.domain.repository

import com.example.mvi_jetpackcompose.domain.models.Book
import kotlinx.coroutines.flow.Flow

interface IBooksRepository {

    suspend fun searchBook(query: String): List<Book>

    fun getSavedBooks(): Flow<List<Book>>
}
