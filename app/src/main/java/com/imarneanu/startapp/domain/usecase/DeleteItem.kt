package com.imarneanu.startapp.domain.usecase

import com.imarneanu.startapp.core.usecase.CommandUseCase1
import com.imarneanu.startapp.domain.model.Item
import com.imarneanu.startapp.domain.source.ItemSource

@Suppress("parameter_name_changed_on_override")
class DeleteItem(private val itemSource: ItemSource): CommandUseCase1<Item> {
    override suspend fun invoke(item: Item) = itemSource.deleteItem(item)
}
