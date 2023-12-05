package com.imarneanu.startapp.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imarneanu.startapp.domain.model.Item
import com.imarneanu.startapp.domain.usecase.DeleteItem
import com.imarneanu.startapp.domain.usecase.InsertItem
import com.imarneanu.startapp.domain.usecase.QueryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemsViewModel(
    queryItems: QueryItem,
    private val insertItem: InsertItem,
    private val deleteItem: DeleteItem,
) : ViewModel() {

    private val _meds =
        queryItems().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ItemsState())

    val state = combine(_state, _meds) { state, meds ->
        state.copy(
            items = meds
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemsState())

    fun onEvent(event: ItemsEvent) {
        when (event) {
            is ItemsEvent.DeleteItem -> viewModelScope.launch { deleteItem(event.item) }
            ItemsEvent.HideDialog -> _state.update { it.copy(isAddingItem = false) }
            ItemsEvent.SaveItem -> {
                val name = state.value.name

                if (name.isBlank()) return

                val item = Item(
                    name = name,
                )

                viewModelScope.launch { insertItem(item) }
                _state.update {
                    it.copy(
                        isAddingItem = false,
                        name = "",
                    )
                }
            }

            is ItemsEvent.SetName -> _state.update { it.copy(name = event.name) }
            ItemsEvent.ShowDialog -> _state.update { it.copy(isAddingItem = true) }
        }
    }
}
