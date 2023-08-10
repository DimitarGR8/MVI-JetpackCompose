package com.example.mvi_jetpackcompose.data.repository

import com.example.mvi_jetpackcompose.core.data.remote.ApiHelper
import com.example.mvi_jetpackcompose.data.local.cache.IFavoriteBooksCache
import com.example.mvi_jetpackcompose.data.local.entity.mapper.mapToBook
import com.example.mvi_jetpackcompose.data.remote.api.BooksApi
import com.example.mvi_jetpackcompose.data.remote.dtos.mapper.mapToDomain
import com.example.mvi_jetpackcompose.domain.models.Book
import com.example.mvi_jetpackcompose.domain.repository.IBooksRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BooksRepository @Inject constructor(
    private val booksApi: BooksApi,
    private val cache: IFavoriteBooksCache,
    private val apiHelper: ApiHelper
) : IBooksRepository {

    override suspend fun searchBook(query: String): List<Book> = withContext(Dispatchers.IO) {
        apiHelper.makeApiCall { booksApi.getBookWithPage(page = "1", query = query) }
            .mapToDomain().books
    }

    override fun getSavedBooks(): Flow<List<Book>> {
        return cache.getAllBooks().map { bookEntityList ->
            bookEntityList.map {
                it.mapToBook()
            }
        }
    }
}
