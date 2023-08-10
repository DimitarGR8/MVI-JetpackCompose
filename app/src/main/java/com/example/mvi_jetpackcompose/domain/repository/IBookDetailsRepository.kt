package com.example.mvi_jetpackcompose.domain.repository

import com.example.mvi_jetpackcompose.domain.models.BookDetails

interface IBookDetailsRepository {

    suspend fun getBookDetails(isbn: String): BookDetails

    suspend fun addToFavourites(bookDetails: BookDetails)

    suspend fun removeFromFavourites(isbn: String)
}
