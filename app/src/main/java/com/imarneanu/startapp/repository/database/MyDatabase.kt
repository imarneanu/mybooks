package com.imarneanu.startapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imarneanu.startapp.repository.item.database.DbItem
import com.imarneanu.startapp.repository.item.database.ItemDao

const val DB_VERSION = 1

@Database(
    entities = [
        DbItem::class,
    ],
    version = DB_VERSION
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        const val DATABASE_NAME = "my_db"
    }
}
