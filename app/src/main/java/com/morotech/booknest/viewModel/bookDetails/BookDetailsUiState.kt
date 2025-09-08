package com.morotech.booknest.viewModel.bookDetails

import com.morotech.domain.model.Book

sealed interface BookDetailsUiState
data object Loading : BookDetailsUiState
data class Loaded(val book: Book) : BookDetailsUiState
data class Error(val message: String) : BookDetailsUiState