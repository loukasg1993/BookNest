package com.morotech.booknest.di

import androidx.room.Room
import com.morotech.data.local.AppDatabase
import com.morotech.data.local.BookDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "books.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<BookDao> { get<AppDatabase>().bookDao() }
}