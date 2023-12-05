package com.imarneanu.startapp.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    state: ItemsState,
    onEvent: (ItemsEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ItemsEvent.ShowDialog) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add item")
            }
        }
    ) { _ ->
        if (state.isAddingItem) {
            AddItemDialog(state = state, onEvent = onEvent)
            return@Scaffold
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.name,
                            fontSize = 20.sp
                        )
                    }
                    IconButton(onClick = { onEvent(ItemsEvent.DeleteItem(item)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete item"
                        )
                    }
                }
            }
        }
    }
}
