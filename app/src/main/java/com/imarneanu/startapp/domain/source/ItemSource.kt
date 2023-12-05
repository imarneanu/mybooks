package com.imarneanu.startapp.domain.source

import com.imarneanu.startapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemSource {

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    fun items(): Flow<List<Item>>
}
