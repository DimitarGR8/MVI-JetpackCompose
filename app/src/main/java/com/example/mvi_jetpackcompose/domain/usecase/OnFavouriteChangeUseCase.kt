package com.example.mvi_jetpackcompose.domain.usecase

import com.example.mvi_jetpackcompose.domain.models.BookDetails
import com.example.mvi_jetpackcompose.domain.repository.IBookDetailsRepository
import javax.inject.Inject

class OnFavouriteChangeUseCase @Inject constructor(
    private val repository: IBookDetailsRepository
) {
    suspend operator fun invoke(isFav: Boolean, bookDetails: BookDetails): BookDetails {
        if (isFav) {
            repository.addToFavourites(bookDetails)
        } else {
            repository.removeFromFavourites(bookDetails.isbn)
        }
        return bookDetails.copy(isFavorite = isFav)
    }
}
