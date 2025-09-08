package com.morotech.data.repository

import com.morotech.data.local.BookDao
import com.morotech.data.local.toDomain
import com.morotech.data.local.toEntity
import com.morotech.data.mapper.toDomain
import com.morotech.data.network.BookApi
import com.morotech.domain.model.Book
import com.morotech.domain.repository.BookRepository

class BookRepositoryImpl(
    private val api: BookApi, private val bookDao: BookDao
) : BookRepository {

    override suspend fun getCachedBooks(): List<Book> =
        bookDao.getAll().map { it.toDomain() }

    override suspend fun getCachedBook(id: Int): Book? =
        bookDao.getById(id)?.toDomain()

    override suspend fun getBooks(page: Int): Result<List<Book>> =
        runCatching {
            val response = api.getBooks(page).results.map { it.toDomain() }
            bookDao.clear()
            bookDao.upsertAll(response.map { it.toEntity() })
            response
        }

    override suspend fun getBookById(id: Int): Result<Book> = runCatching {
        api.getBookDetails(id).toDomain()
    }
}