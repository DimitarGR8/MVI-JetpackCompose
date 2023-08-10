package com.example.mvi_jetpackcompose.data.remote.dtos

import com.google.gson.annotations.SerializedName

class BookPageDto(
    @SerializedName("books")
    val books: List<BookDto>
)
