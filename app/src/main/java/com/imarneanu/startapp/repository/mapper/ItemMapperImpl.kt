package com.imarneanu.startapp.repository.mapper

import com.imarneanu.startapp.domain.model.Item
import com.imarneanu.startapp.repository.item.database.DbItem

class ItemMapperImpl : ItemMapper {

    override fun toItems(dbItems: List<DbItem>): List<Item> = dbItems.map(::toMedicine)

    override fun toDbItem(item: Item): DbItem = with(item) {
        DbItem(
            name = name,
        )
    }

    private fun toMedicine(dbItem: DbItem) = with(dbItem) {
        Item(
            name = name,
        )
    }
}
