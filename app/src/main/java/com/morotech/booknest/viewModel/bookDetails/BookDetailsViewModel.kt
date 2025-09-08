package com.morotech.booknest.viewModel.bookDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morotech.domain.usecase.GetBookDetailsUseCase
import com.morotech.domain.usecase.GetCachedBookUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val getBookDetails: GetBookDetailsUseCase,
    private val getCachedBook: GetCachedBookUseCase
) : ViewModel() {

    val state = MutableStateFlow<BookDetailsUiState>(Loading)

    fun load(id: Int) {
        viewModelScope.launch {
            val cached = getCachedBook(id)
            if (cached != null) {
                state.value = Loaded(cached)
            } else {
                state.value = Loading
            }
            val res = getBookDetails(id)
            res.onSuccess { book ->
                state.value = Loaded(book)
            }.onFailure {
                if (cached == null) {
                    state.value = Error("Failed to load details")
                }
            }
        }
    }
}