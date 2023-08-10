package com.example.mvi_jetpackcompose.data.local.entity.mapper

import com.example.mvi_jetpackcompose.data.local.entity.BookEntity
import com.example.mvi_jetpackcompose.domain.models.Book
import com.example.mvi_jetpackcompose.domain.models.BookDetails

fun BookEntity.mapToDomain(): BookDetails {
    return BookDetails(
        title = title,
        subtitle = subTitle,
        authors = authors,
        isbn = id,
        pages = pages,
        year = year,
        rating = rating,
        description = description,
        price = price,
        image = image,
        url = url
    )
}

fun BookDetails.mapFromDomain(): BookEntity {
    return BookEntity(
        title = title,
        subTitle = subtitle,
        authors = authors,
        id = isbn,
        pages = pages,
        year = year,
        rating = rating,
        description = description,
        price = price,
        image = image,
        url = url
    )
}

fun BookEntity.mapToBook(): Book {
    return Book(title, subTitle, id, price, image, url)
}
