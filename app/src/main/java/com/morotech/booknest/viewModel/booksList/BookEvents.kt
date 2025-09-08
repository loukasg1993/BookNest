package com.morotech.booknest.viewModel.booksList

import com.morotech.domain.model.Book

sealed interface BookListEvent {
    data class ItemClicked(val book: Book) : BookListEvent
    data object Refresh: BookListEvent
}


sealed interface BookListAction {
    data class NavigateToDetails(val id: Int) : BookListAction
    data class ShowToast(val message: String) : BookListAction
}