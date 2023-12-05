package com.imarneanu.startapp.ui.items

import com.imarneanu.startapp.domain.model.Item

data class ItemsState(
    val items: List<Item> = emptyList(),
    val name: String = "",
    val isAddingItem: Boolean = false,
)
