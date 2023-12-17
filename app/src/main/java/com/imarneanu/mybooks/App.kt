package com.imarneanu.mybooks

import android.app.Application
import com.imarneanu.mybooks.repository.database.databaseModule
import com.imarneanu.mybooks.repository.book.di.bookLibModule
import com.imarneanu.mybooks.ui.books.booksModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                bookLibModule,
                booksModule,
            )
        }
    }
}
