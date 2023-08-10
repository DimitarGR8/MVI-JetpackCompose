package com.example.mvi_jetpackcompose.domain.usecase

import com.example.mvi_jetpackcompose.domain.models.Book
import com.example.mvi_jetpackcompose.domain.repository.IBooksRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val booksRepository: IBooksRepository
) {
    suspend operator fun invoke(query: String): List<Book> {
        return booksRepository.searchBook(query = query)
    }
}
