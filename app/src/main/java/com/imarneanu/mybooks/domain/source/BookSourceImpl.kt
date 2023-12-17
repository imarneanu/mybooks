package com.imarneanu.mybooks.domain.source

import com.imarneanu.mybooks.domain.model.Book
import com.imarneanu.mybooks.repository.book.repository.BookRepository
import com.imarneanu.mybooks.repository.mapper.BookMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookSourceImpl(
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapper,
) : BookSource {

    private val meds = bookRepository.books().map(bookMapper::toBooks)

    override suspend fun insertBook(book: Book) =
        bookRepository.insertBook(bookMapper.toDbBook(book))

    override suspend fun deleteBook(book: Book) =
        bookRepository.deleteBook(bookMapper.toDbBook(book))

    override fun books(): Flow<List<Book>> = meds
}
