package com.morotech.booknest.viewModel.booksList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morotech.domain.usecase.GetBooksUseCase
import com.morotech.domain.usecase.GetCachedBooksUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BookListViewModel(
    private val getBooks: GetBooksUseCase,
    private val getCachedBooks: GetCachedBooksUseCase
) : ViewModel() {

    val state = MutableStateFlow<BookListUiState>(Loading)

    val action = MutableSharedFlow<BookListAction>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private var currentPage = 1

    init {
        viewModelScope.launch {
            val cached = getCachedBooks()
            if (cached.isNotEmpty()) {
                state.value = Loaded(cached)
            } else {
                state.value = Loading
            }
            refresh()
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            val res = getBooks(currentPage)
            res.onSuccess { list ->
                state.value = Loaded(list)
            }.onFailure {
//                state.value = Error("Failed to load books")
                action.emit(BookListAction.ShowToast("Failed to load books"))
            }
        }
    }

    fun onEvent(event: BookListEvent) {
        when(event) {
            is BookListEvent.ItemClicked -> {
                action.tryEmit(BookListAction.NavigateToDetails(event.book.id))
            }
            is BookListEvent.Refresh -> refresh()
        }
    }
}