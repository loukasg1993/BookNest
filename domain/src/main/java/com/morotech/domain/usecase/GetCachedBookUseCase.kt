package com.morotech.domain.usecase

import com.morotech.domain.model.Book
import com.morotech.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCachedBookUseCase(private val repo: BookRepository) {
    suspend operator fun invoke(id: Int): Book? =
        withContext(Dispatchers.IO) { repo.getCachedBook(id) }
}