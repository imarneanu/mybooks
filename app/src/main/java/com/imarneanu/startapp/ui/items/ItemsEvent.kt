package com.imarneanu.startapp.ui.items

import com.imarneanu.startapp.domain.model.Item

sealed interface ItemsEvent{
    object SaveItem: ItemsEvent
    data class SetName(val name: String): ItemsEvent
    object ShowDialog: ItemsEvent
    object HideDialog: ItemsEvent
    data class DeleteItem(val item: Item): ItemsEvent
}
