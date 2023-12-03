package com.imarneanu.startapp.repository.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MyMedsDatabase::class.java,
            MyMedsDatabase.DATABASE_NAME
        )
            .addMigrations(MyMedsDatabase.extendDatabaseMigration)
            .fallbackToDestructiveMigration()
            .build()
    }
}
