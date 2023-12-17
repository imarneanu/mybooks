package com.imarneanu.mybooks.repository.mapper

import com.imarneanu.mybooks.domain.model.Book
import com.imarneanu.mybooks.repository.book.database.DbBook

interface BookMapper {

    fun toBooks(dbBooks: List<DbBook>): List<Book>

    fun toDbBook(book: Book): DbBook
}
