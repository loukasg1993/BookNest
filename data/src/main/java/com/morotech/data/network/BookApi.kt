package com.morotech.data.network

import com.morotech.data.model.BookResponse
import com.morotech.data.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {
    @GET("books")
    suspend fun getBooks(@Query("page") page: Int = 1): BooksResponse

    @GET("books/{id}")
    suspend fun getBookDetails(@Path("id") id: Int): BookResponse
}