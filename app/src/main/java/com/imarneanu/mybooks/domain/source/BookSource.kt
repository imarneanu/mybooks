package com.imarneanu.mybooks.domain.source

import com.imarneanu.mybooks.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookSource {

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(book: Book)

    fun books(): Flow<List<Book>>
}
