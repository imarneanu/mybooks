package com.imarneanu.mybooks.ui.books

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    state: BooksState,
    onEvent: (BookEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(BookEvent.ShowBookDialog()) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add book")
            }
        }
    ) { _ ->
        if (state.showBookDialog) {
            BookDialog(state = state, onEvent = onEvent)
            return@Scaffold
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.books) { book ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${book.name}. ${book.author}",
                            color = if (book.isForChildren) Color.Blue else Color.Black,
                            fontSize = 20.sp
                        )
                    }
                    IconButton(onClick = { onEvent(BookEvent.ShowBookDialog(book)) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Update book"
                        )
                    }
                    IconButton(onClick = { onEvent(BookEvent.DeleteBook(book)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete book"
                        )
                    }
                }
            }
        }
    }
}
