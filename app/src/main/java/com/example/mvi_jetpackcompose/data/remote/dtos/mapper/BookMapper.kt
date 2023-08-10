package com.example.mvi_jetpackcompose.data.remote.dtos.mapper

import com.example.mvi_jetpackcompose.data.remote.dtos.BookDetailsDto
import com.example.mvi_jetpackcompose.data.remote.dtos.BookDto
import com.example.mvi_jetpackcompose.data.remote.dtos.BookPageDto
import com.example.mvi_jetpackcompose.domain.models.Book
import com.example.mvi_jetpackcompose.domain.models.BookDetails
import com.example.mvi_jetpackcompose.domain.models.BookPage

fun BookDto.mapToDomain(): Book {
    return Book(
        title = title,
        subtitle = subtitle,
        isbn = isbn,
        price = price,
        imageUrl = imageUrl,
        bookUrl = bookUrl
    )
}

fun BookPageDto.mapToDomain(): BookPage {
    return BookPage(
        books = books.map { it.mapToDomain() }
    )
}

fun BookDetailsDto.mapToDomain(): BookDetails {
    return BookDetails(
        title = title,
        subtitle = subtitle,
        authors = authors,
        isbn = isbn,
        pages = pages,
        year = year,
        rating = rating,
        description = description,
        price = price,
        image = image,
        url = url
    )
}
