package com.morotech.booknest.viewModel

import app.cash.turbine.test
import com.morotech.booknest.viewModel.booksList.BookListAction
import com.morotech.booknest.viewModel.booksList.BookListEvent
import com.morotech.booknest.viewModel.booksList.BookListViewModel
import com.morotech.booknest.viewModel.booksList.Loaded
import com.morotech.domain.model.Book
import com.morotech.domain.usecase.GetBooksUseCase
import com.morotech.domain.usecase.GetCachedBooksUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BookListViewModelTest {

    private val getBooks: GetBooksUseCase = mockk()
    private val getCachedBooks: GetCachedBooksUseCase = mockk()
    private lateinit var vm: BookListViewModel

    private val testBook = Book(
        id = 1,
        title = "Test",
        authors = emptyList(),
        coverUrl = null,
        subjects = listOf("Adventure")
    )

    @Before
    fun setup() {
        coEvery { getCachedBooks() } returns listOf(testBook)
        coEvery { getBooks(any()) } returns Result.success(listOf(testBook))

        vm = BookListViewModel(getBooks, getCachedBooks)
    }

    @Test
    fun `emits cached books on init`() = runTest {
        vm.state.test {
            val first = awaitItem()
            assert(first is Loaded && first.books.first().title == "Test")
        }
    }

    @Test
    fun `navigates to details when item clicked`() = runTest {
        vm.action.test {
            vm.onEvent(BookListEvent.ItemClicked(testBook))
            val action = awaitItem()
            assert(action is BookListAction.NavigateToDetails && action.id == 1)
        }
    }
}