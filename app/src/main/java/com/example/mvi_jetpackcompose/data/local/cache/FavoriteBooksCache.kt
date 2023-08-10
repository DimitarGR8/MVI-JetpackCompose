package com.example.mvi_jetpackcompose.data.local.cache

import com.example.mvi_jetpackcompose.data.local.entity.BookEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FavoriteBooksCache @Inject constructor() : IFavoriteBooksCache {

    /**
     * Used fake data for simplicity
     */
    private val fakeCachedBooks = mutableListOf<BookEntity>()

    private val booksFlow = MutableSharedFlow<List<BookEntity>>(replay = 1)

    override fun getAllBooks(): Flow<List<BookEntity>> {
        return booksFlow
    }

    override suspend fun getBookById(id: String): BookEntity? {
        return fakeCachedBooks.find { it.id == id }
    }

    override suspend fun deleteBookById(id: String) {
        fakeCachedBooks.removeIf { it.id == id }
        booksFlow.emit(fakeCachedBooks)
    }

    override suspend fun insertBook(entity: BookEntity) {
        fakeCachedBooks.add(
            BookEntity(
                id = entity.id,
                title = entity.title,
                subTitle = entity.subTitle,
                authors = entity.authors,
                pages = entity.pages,
                year = entity.year,
                rating = entity.rating,
                description = entity.description,
                price = entity.price,
                image = entity.image,
                url = entity.url
            )
        )
        booksFlow.emit(fakeCachedBooks)
    }
}
