package com.example.mvi_jetpackcompose.data.remote.api

import com.example.mvi_jetpackcompose.data.remote.dtos.BookDetailsDto
import com.example.mvi_jetpackcompose.data.remote.dtos.BookPageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {

    @GET(ApiEndpoints.SEARCH_ENDPOINT + "/{query}/{page}")
    suspend fun getBookWithPage(
        @Path("page") page: String,
        @Path("query") query: String = "Algo"
    ): Response<BookPageDto>

    @GET(ApiEndpoints.DETAILS + "/{isbn}")
    suspend fun getBookDetails(@Path("isbn") isbn: String): Response<BookDetailsDto>
}
