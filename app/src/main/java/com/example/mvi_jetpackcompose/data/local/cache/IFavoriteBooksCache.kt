package com.example.mvi_jetpackcompose.data.local.cache

import com.example.mvi_jetpackcompose.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

interface IFavoriteBooksCache {

    fun getAllBooks(): Flow<List<BookEntity>>

    suspend fun getBookById(id: String): BookEntity?

    suspend fun insertBook(entity: BookEntity)

    suspend fun deleteBookById(id: String)
}
