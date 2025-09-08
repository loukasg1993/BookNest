package com.morotech.domain.usecase

import com.morotech.domain.model.Book
import com.morotech.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCachedBooksUseCase(private val repo: BookRepository) {
    suspend operator fun invoke(): List<Book> =
        withContext(Dispatchers.IO) { repo.getCachedBooks() }
}