package com.morotech.booknest.di

import com.morotech.booknest.viewModel.bookDetails.BookDetailsViewModel
import com.morotech.booknest.viewModel.booksList.BookListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BookListViewModel(get(), get()) }
    viewModel { BookDetailsViewModel(get(), get()) }
}