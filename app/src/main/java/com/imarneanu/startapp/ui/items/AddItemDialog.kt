package com.imarneanu.startapp.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemDialog(
    state: ItemsState,
    onEvent: (ItemsEvent) -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onEvent(ItemsEvent.HideDialog) },
        confirmButton = {
            Button(onClick = { onEvent(ItemsEvent.SaveItem) }) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(onClick = { onEvent(ItemsEvent.HideDialog) }) {
                Text(text = "Cancel")
            }
        },
        title = { Text("Add item") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(value = state.name, onValueChange = {
                    onEvent(ItemsEvent.SetName(it))
                },
                    placeholder = {
                        Text(text = "Name")
                    })
            }
        }
    )
}
