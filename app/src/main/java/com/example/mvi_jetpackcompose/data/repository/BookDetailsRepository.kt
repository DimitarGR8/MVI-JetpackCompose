package com.example.mvi_jetpackcompose.data.repository

import com.example.mvi_jetpackcompose.core.data.remote.ApiHelper
import com.example.mvi_jetpackcompose.data.local.cache.IFavoriteBooksCache
import com.example.mvi_jetpackcompose.data.local.entity.mapper.mapFromDomain
import com.example.mvi_jetpackcompose.data.local.entity.mapper.mapToDomain
import com.example.mvi_jetpackcompose.data.remote.api.BooksApi
import com.example.mvi_jetpackcompose.data.remote.dtos.mapper.mapToDomain
import com.example.mvi_jetpackcompose.domain.models.BookDetails
import com.example.mvi_jetpackcompose.domain.repository.IBookDetailsRepository
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class BookDetailsRepository @Inject constructor(
    private val booksApi: BooksApi,
    private val cache: IFavoriteBooksCache,
    private val apiHelper: ApiHelper,
    @Named("io")
    private val ioDispatcher: CoroutineDispatcher
) : IBookDetailsRepository {

    override suspend fun getBookDetails(isbn: String): BookDetails =
        withContext(ioDispatcher) {
            // if cached return it else do network call
            cache.getBookById(isbn)?.let {
                it.mapToDomain().apply {
                    isFavorite = true
                }
            } ?: run {
                apiHelper.makeApiCall { booksApi.getBookDetails(isbn) }.mapToDomain()
            }
        }

    override suspend fun addToFavourites(bookDetails: BookDetails) {
        cache.insertBook(bookDetails.mapFromDomain())
    }

    override suspend fun removeFromFavourites(isbn: String) {
        cache.deleteBookById(isbn)
    }
}
