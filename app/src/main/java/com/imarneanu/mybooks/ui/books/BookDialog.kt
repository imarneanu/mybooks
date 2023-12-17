package com.imarneanu.mybooks.ui.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDialog(
    state: BooksState,
    onEvent: (BookEvent) -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onEvent(BookEvent.HideDialog) },
        confirmButton = {
            Button(onClick = { onEvent(BookEvent.SaveBook) }) {
                Text(text = state.id?.let { "Update" } ?: "Save")
            }
        },
        dismissButton = {
            Button(onClick = { onEvent(BookEvent.HideDialog) }) {
                Text(text = "Cancel")
            }
        },
        title = { Text(state.id?.let { "Update book" } ?: "Add book") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(value = state.name, onValueChange = {
                    onEvent(BookEvent.SetName(it))
                },
                    placeholder = {
                        Text(text = "Name")
                    })
                TextField(value = state.author, onValueChange = {
                    onEvent(BookEvent.SetAuthor(it))
                },
                    placeholder = {
                        Text(text = "Author")
                    })
                TextField(value = state.publication, onValueChange = {
                    onEvent(BookEvent.SetPublication(it))
                },
                    placeholder = {
                        Text(text = "Publication")
                    })
                TextField(value = state.quantity.toString(), onValueChange = {
                    onEvent(BookEvent.SetQuantity(it))
                },
                    placeholder = {
                        Text(text = "Quantity")
                    })
                TextField(value = state.details, onValueChange = {
                    onEvent(BookEvent.SetDetails(it))
                },
                    placeholder = {
                        Text(text = "Details")
                    })
                Row {
                    Text(text = "Children book")
                    Switch(
                        checked = state.isForChildren,
                        onCheckedChange = { onEvent(BookEvent.SetIsForChildren(it)) })
                }
            }
        }
    )
}
