package com.morotech.booknest.viewModel.booksList

import com.morotech.domain.model.Book

sealed interface BookListUiState
data object Loading : BookListUiState
data class Loaded(val books: List<Book>) : BookListUiState
data class Error(val message: String) : BookListUiState