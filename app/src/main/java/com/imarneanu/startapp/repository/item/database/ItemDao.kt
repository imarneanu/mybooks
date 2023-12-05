package com.imarneanu.startapp.repository.item.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Upsert
    suspend fun insertOrUpdate(dbItem: DbItem)

    @Transaction
    @Query("SELECT * FROM Item")
    fun getAll(): Flow<List<DbItem>>

    @Delete
    suspend fun delete(dbItem: DbItem)

    @Query("DELETE FROM Item")
    suspend fun deleteAll()
}
