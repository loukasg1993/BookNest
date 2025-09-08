package com.morotech.data.repository

import com.morotech.data.local.BookDao
import com.morotech.data.local.BookEntity
import com.morotech.data.model.AuthorResponse
import com.morotech.data.model.BookResponse
import com.morotech.data.model.BooksResponse
import com.morotech.data.network.BookApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import io.mockk.mockk
import io.mockk.coEvery
import io.mockk.coVerify

class BookRepositoryImplTest {

    private val api: BookApi = mockk()
    private val dao: BookDao = mockk(relaxed = true)
    private lateinit var repository: BookRepositoryImpl

    @Before
    fun setup() {
        repository = BookRepositoryImpl(api, dao)
    }

    @Test
    fun `getBooks fetches from api and caches in dao`() = runTest {
        val response = BooksResponse(
            count = 1,
            next = null,
            previous = null,
            results = listOf(
                BookResponse(
                    id = 1,
                    title = "Moby Dick",
                    authors = listOf(AuthorResponse("Melville, Herman", 1819, 1891)),
                    subjects = listOf("Sea stories"),
                    formats = mapOf("image/jpeg" to "https://example.com/cover.jpg")
                )
            )
        )

        coEvery { api.getBooks(1) } returns response

        val result = repository.getBooks(1)

        assertTrue(result.isSuccess)
        val books = result.getOrNull()
        assertEquals("Moby Dick", books?.first()?.title)

        coVerify { dao.clear() }
        coVerify { dao.upsertAll(any()) }
    }

    @Test
    fun `getCachedBooks returns mapped domain books`() = runTest {
        val entity = BookEntity(
            id = 1,
            title = "Cached Book",
            authorName = "Author",
            authorBirthYear = 1900,
            authorDeathYear = 1950,
            coverUrl = "cover.jpg",
            subject = "Adventure"
        )
        coEvery { dao.getAll() } returns listOf(entity)

        val result = repository.getCachedBooks()

        assertEquals(1, result.size)
        assertEquals("Cached Book", result.first().title)
        assertEquals("Author", result.first().authors.first().name)
    }
}