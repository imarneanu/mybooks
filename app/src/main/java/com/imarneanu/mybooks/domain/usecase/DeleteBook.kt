package com.imarneanu.mybooks.domain.usecase

import com.imarneanu.mybooks.core.usecase.CommandUseCase1
import com.imarneanu.mybooks.domain.model.Book
import com.imarneanu.mybooks.domain.source.BookSource

@Suppress("parameter_name_changed_on_override")
class DeleteBook(private val bookSource: BookSource) : CommandUseCase1<Book> {
    override suspend fun invoke(book: Book) = bookSource.deleteBook(book)
}
