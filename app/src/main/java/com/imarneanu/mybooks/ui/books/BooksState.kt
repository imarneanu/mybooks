package com.imarneanu.mybooks.ui.books

import com.imarneanu.mybooks.domain.model.Book

data class BooksState(
    val books: List<Book> = emptyList(),
    val id: Int? = 0,
    val name: String = "",
    val author: String = "",
    val publication: String = "",
    val quantity: String = "",
    val details: String = "",
    val isForChildren: Boolean = false,
    val showBookDialog: Boolean = false,
)
