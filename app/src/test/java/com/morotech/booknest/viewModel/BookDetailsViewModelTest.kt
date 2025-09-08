package com.morotech.booknest.viewModel

import app.cash.turbine.test
import com.morotech.booknest.viewModel.bookDetails.BookDetailsViewModel
import com.morotech.booknest.viewModel.bookDetails.Loaded
import com.morotech.domain.model.Book
import com.morotech.domain.usecase.GetBookDetailsUseCase
import com.morotech.domain.usecase.GetCachedBookUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BookDetailsViewModelTest {

    private val getDetails: GetBookDetailsUseCase = mockk()
    private val getCached: GetCachedBookUseCase = mockk()
    private lateinit var vm: BookDetailsViewModel

    private val fakeBook = Book(
        id = 1,
        title = "Detail Test",
        authors = emptyList(),
        coverUrl = null,
        subjects = listOf("Classic")
    )

    @Before
    fun setup() {
        vm = BookDetailsViewModel(getDetails, getCached)
    }

    @Test
    fun `loads cached book first`() = runTest {
        coEvery { getCached(1) } returns fakeBook
        coEvery { getDetails(1) } returns Result.success(fakeBook)

        vm.load(1)

        vm.state.test {
            val first = awaitItem()
            assert(first is Loaded && first.book.title == "Detail Test")
        }
    }

}