package com.morotech.domain.usecase

import com.morotech.domain.model.Book
import com.morotech.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetBooksUseCase(private val repo: BookRepository) {
    suspend operator fun invoke(page: Int): Result<List<Book>> =
        withContext(Dispatchers.IO) { repo.getBooks(page) }
}