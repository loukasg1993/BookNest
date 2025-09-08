package com.morotech.domain.usecase

import com.morotech.domain.model.Book
import com.morotech.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetBookDetailsUseCase(private val repo: BookRepository) {
    suspend operator fun invoke(id: Int): Result<Book> =
        withContext(Dispatchers.IO) { repo.getBookById(id) }
}