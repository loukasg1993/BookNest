package com.morotech.domain.repository

import com.morotech.domain.model.Book

interface BookRepository {
    suspend fun getBooks(page: Int): Result<List<Book>>
    suspend fun getBookById(id: Int): Result<Book>
    suspend fun getCachedBooks(): List<Book>
    suspend fun getCachedBook(id: Int): Book?
}