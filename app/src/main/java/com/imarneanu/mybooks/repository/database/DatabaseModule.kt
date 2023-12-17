package com.imarneanu.mybooks.repository.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MyDatabase::class.java,
            MyDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
