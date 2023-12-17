package com.imarneanu.mybooks.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imarneanu.mybooks.repository.book.database.BookDao
import com.imarneanu.mybooks.repository.book.database.DbBook

const val DB_VERSION = 1

@Database(
    entities = [
        DbBook::class,
    ],
    version = DB_VERSION
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        const val DATABASE_NAME = "my_books"
    }
}
