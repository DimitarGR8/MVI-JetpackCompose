package com.example.mvi_jetpackcompose.domain.models

data class BookDetails(
    val title: String,
    val subtitle: String,
    val authors: String,
    val isbn: String,
    val pages: String,
    val year: String,
    val rating: String,
    val description: String,
    val price: String,
    val image: String,
    val url: String,
    var isFavorite: Boolean = false
)
