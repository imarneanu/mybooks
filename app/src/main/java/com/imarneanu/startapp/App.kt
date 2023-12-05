package com.imarneanu.startapp

import android.app.Application
import com.imarneanu.startapp.repository.database.databaseModule
import com.imarneanu.startapp.repository.item.di.itemLibModule
import com.imarneanu.startapp.ui.items.itemsModule
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
                itemLibModule,
                itemsModule,
            )
        }
    }
}
