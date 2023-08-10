package com.example.mvi_jetpackcompose.home

import com.example.mvi_jetpackcompose.domain.models.Book

data class HomeState(
    val books: List<Book> = emptyList(),
    val isDarkMode: Boolean = false,
    val query: String = "",
    val isBottomSheetOpen:Boolean = true,
)
