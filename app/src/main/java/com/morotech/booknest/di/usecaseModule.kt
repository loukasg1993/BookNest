package com.morotech.booknest.di

import com.morotech.domain.usecase.GetBookDetailsUseCase
import com.morotech.domain.usecase.GetBooksUseCase
import com.morotech.domain.usecase.GetCachedBookUseCase
import com.morotech.domain.usecase.GetCachedBooksUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetBooksUseCase(get()) }
    factory { GetBookDetailsUseCase(get()) }
    factory { GetCachedBooksUseCase(get()) }
    factory { GetCachedBookUseCase(get()) }
}