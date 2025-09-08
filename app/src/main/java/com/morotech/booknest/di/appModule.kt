package com.morotech.booknest.di

import com.morotech.booknest.viewModel.bookDetails.BookDetailsViewModel
import com.morotech.booknest.viewModel.booksList.BookListViewModel
import com.morotech.data.repository.BookRepositoryImpl
import com.morotech.domain.repository.BookRepository
import com.morotech.domain.usecase.GetBookDetailsUseCase
import com.morotech.domain.usecase.GetBooksUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Repository
    single<BookRepository> { BookRepositoryImpl(get(), get()) }

    // Use cases
    factory { GetBooksUseCase(get()) }
    factory { GetBookDetailsUseCase(get()) }

    // ViewModels
    viewModel { BookListViewModel(get(), get()) }
    viewModel { BookDetailsViewModel(get(), get()) }
}