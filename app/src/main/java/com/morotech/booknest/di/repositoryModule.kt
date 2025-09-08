package com.morotech.booknest.di

import com.morotech.data.repository.BookRepositoryImpl
import com.morotech.domain.repository.BookRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BookRepository> { BookRepositoryImpl(get(), get()) }
}