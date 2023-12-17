package com.imarneanu.mybooks.ui.books

import com.imarneanu.mybooks.domain.model.Book

sealed interface BookEvent{
    object SaveBook: BookEvent
    data class SetName(val name: String): BookEvent
    data class SetAuthor(val name: String): BookEvent
    data class SetPublication(val publication: String): BookEvent
    data class SetQuantity(val quantity: String): BookEvent
    data class SetDetails(val details: String): BookEvent
    data class SetIsForChildren(val isForChildren: Boolean): BookEvent
    data class ShowBookDialog(val book: Book? = null): BookEvent
    object HideDialog: BookEvent
    data class DeleteBook(val book: Book): BookEvent
}
