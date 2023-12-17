package com.imarneanu.mybooks.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imarneanu.mybooks.domain.model.Book
import com.imarneanu.mybooks.domain.usecase.DeleteBook
import com.imarneanu.mybooks.domain.usecase.InsertBook
import com.imarneanu.mybooks.domain.usecase.QueryBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksViewModel(
    queryBooks: QueryBook,
    private val insertBook: InsertBook,
    private val deleteBook: DeleteBook,
) : ViewModel() {

    private val _books =
        queryBooks().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(BooksState())

    val state = combine(_state, _books) { state, books ->
        state.copy(
            books = books
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BooksState())

    fun onEvent(event: BookEvent) {
        when (event) {
            is BookEvent.DeleteBook -> viewModelScope.launch { deleteBook(event.book) }
            BookEvent.HideDialog -> _state.update { it.copy(showBookDialog = false) }
            BookEvent.SaveBook -> {
                val id = state.value.id
                val name = state.value.name
                val author = state.value.author
                val publication = state.value.publication
                val quantity = state.value.quantity
                val details = state.value.details
                val isForChildren = state.value.isForChildren

                if (name.isBlank()) return

                val book = Book(
                    id = id ?: 0,
                    name = name,
                    author = author,
                    publication = publication,
                    quantity = if (quantity.isEmpty() || quantity == "null") 1 else quantity.toInt(),
                    details = details,
                    isForChildren = isForChildren,
                )

                viewModelScope.launch { insertBook(book) }
                _state.update {
                    it.copy(
                        showBookDialog = false,
                        name = "",
                        author = "",
                        publication = "",
                        quantity = "",
                        details = "",
                        isForChildren = false,
                    )
                }
            }

            is BookEvent.SetName -> _state.update { it.copy(name = event.name) }
            is BookEvent.SetAuthor -> _state.update { it.copy(author = event.name) }
            is BookEvent.SetPublication -> _state.update { it.copy(publication = event.publication) }
            is BookEvent.SetQuantity -> _state.update { it.copy(quantity = event.quantity) }
            is BookEvent.SetDetails -> _state.update { it.copy(details = event.details) }
            is BookEvent.SetIsForChildren -> _state.update { it.copy(isForChildren = event.isForChildren) }
            is BookEvent.ShowBookDialog -> _state.update {
                it.copy(
                    showBookDialog = true,
                    id = event.book?.id,
                    name = event.book?.name ?: "",
                    author = event.book?.author ?: "",
                    publication = event.book?.publication ?: "",
                    quantity = event?.book?.quantity?.toString() ?: "",
                    details = event.book?.details ?: "",
                    isForChildren = event.book?.isForChildren ?: false,
                )
            }
        }
    }
}
