package com.example.mvi_jetpackcompose.domain.usecase

import com.example.mvi_jetpackcompose.domain.models.BookDetails
import com.example.mvi_jetpackcompose.domain.repository.IBookDetailsRepository
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(
    private val repository: IBookDetailsRepository
) {

    suspend operator fun invoke(isbn: String): BookDetails {
        return repository.getBookDetails(isbn)
    }
}
