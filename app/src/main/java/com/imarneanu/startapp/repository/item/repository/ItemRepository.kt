package com.imarneanu.startapp.repository.item.repository

import com.imarneanu.startapp.repository.item.database.DbItem
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun insertItem(dbItem: DbItem)

    suspend fun deleteItem(dbItem: DbItem)

    suspend fun deleteAll()

    fun items(): Flow<List<DbItem>>
}
