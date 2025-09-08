package com.morotech.booknest.di

import com.morotech.data.network.BookApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { OkHttpClient.Builder().build() }
    single {
        Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build() }

    single {
        Retrofit.Builder()
            .baseUrl("https://gutendex.com/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<BookApi> { get<Retrofit>().create(BookApi::class.java) }
}