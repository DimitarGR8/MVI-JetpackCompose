package com.example.mvi_jetpackcompose.data.local.entity

data class BookEntity(
    val id: String,
    val title: String,
    val subTitle: String,
    val authors: String,
    val pages: String,
    val year: String,
    val rating: String,
    val description: String,
    val price: String,
    val image: String,
    val url: String
)
