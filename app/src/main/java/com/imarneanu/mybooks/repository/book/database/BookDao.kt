package com.imarneanu.mybooks.repository.book.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Upsert
    suspend fun insertOrUpdate(dbBook: DbBook)

    @Transaction
    @Query("SELECT * FROM Book")
    fun getAll(): Flow<List<DbBook>>

    @Delete
    suspend fun delete(dbBook: DbBook)

    @Query("DELETE FROM Book")
    suspend fun deleteAll()
}
