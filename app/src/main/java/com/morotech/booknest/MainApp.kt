package com.morotech.booknest

import android.app.Application
import com.morotech.booknest.di.appModule
import com.morotech.booknest.di.databaseModule
import com.morotech.booknest.di.networkModule
import com.morotech.booknest.di.repositoryModule
import com.morotech.booknest.di.useCaseModule
import com.morotech.booknest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(appModule, networkModule, databaseModule, repositoryModule, viewModelModule, useCaseModule)
        }
    }
}