package com.imarneanu.startapp.repository.mapper

import com.imarneanu.startapp.domain.model.Item
import com.imarneanu.startapp.repository.item.database.DbItem

interface ItemMapper {

    fun toItems(dbItems: List<DbItem>): List<Item>

    fun toDbItem(item: Item): DbItem
}
