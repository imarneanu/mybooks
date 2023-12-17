package com.imarneanu.mybooks.ui.books

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val booksModule = module {
    viewModel {
        BooksViewModel(
            queryBooks = get(),
            insertBook = get(),
            deleteBook = get(),
        )
    }
}
