package com.example.mvi_jetpackcompose.domain.usecase

import com.example.mvi_jetpackcompose.domain.models.Book
import com.example.mvi_jetpackcompose.domain.repository.IBooksRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetFavoriteBooksUseCase @Inject constructor(
    private val repository: IBooksRepository
) {
    operator fun invoke(): Flow<List<Book>> = repository.getSavedBooks()
}
