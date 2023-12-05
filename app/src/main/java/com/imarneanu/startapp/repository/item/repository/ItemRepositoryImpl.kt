package com.imarneanu.startapp.repository.item.repository

import com.imarneanu.startapp.repository.item.database.DbItem
import com.imarneanu.startapp.repository.item.database.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(private val itemDao: ItemDao) : ItemRepository {

    override suspend fun insertItem(dbItem: DbItem) =
        itemDao.insertOrUpdate(dbItem)

    override suspend fun deleteItem(dbItem: DbItem) = itemDao.delete(dbItem)

    override suspend fun deleteAll() = itemDao.deleteAll()

    override fun items(): Flow<List<DbItem>> = itemDao.getAll()
}
