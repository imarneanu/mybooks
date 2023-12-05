package com.imarneanu.startapp.domain.source

import com.imarneanu.startapp.domain.model.Item
import com.imarneanu.startapp.repository.item.repository.ItemRepository
import com.imarneanu.startapp.repository.mapper.ItemMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemSourceImpl(
    private val itemRepository: ItemRepository,
    private val itemMapper: ItemMapper,
) : ItemSource {

    private val meds = itemRepository.items().map(itemMapper::toItems)

    override suspend fun insertItem(item: Item) =
        itemRepository.insertItem(itemMapper.toDbItem(item))

    override suspend fun deleteItem(item: Item) =
        itemRepository.deleteItem(itemMapper.toDbItem(item))

    override fun items(): Flow<List<Item>> = meds
}
