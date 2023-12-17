package com.imarneanu.mybooks.domain.usecase

import com.imarneanu.mybooks.core.usecase.QueryUseCase
import com.imarneanu.mybooks.domain.model.Book
import com.imarneanu.mybooks.domain.source.BookSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QueryBook(private val bookSource: BookSource) : QueryUseCase<List<Book>> {
    override fun invoke(): Flow<List<Book>> = bookSource.books().map { books ->
        books.sortedWith(
            compareBy({ it.name.lowercase() }, { it.author.lowercase() })
        )
    }
}
