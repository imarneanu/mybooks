package com.imarneanu.mybooks.repository.mapper

import com.imarneanu.mybooks.domain.model.Book
import com.imarneanu.mybooks.repository.book.database.DbBook

class BookMapperImpl : BookMapper {

    override fun toBooks(dbBooks: List<DbBook>): List<Book> = dbBooks.map(::toBook)

    override fun toDbBook(book: Book): DbBook = with(book) {
        DbBook(
            id = id,
            name = name,
            author = author,
            publication = publication,
            quantity = quantity,
            details = details,
            isForChildren = isForChildren,
        )
    }

    private fun toBook(dbBook: DbBook) = with(dbBook) {
        Book(
            id = id,
            name = name,
            author = author ?: "",
            publication = publication ?: "",
            quantity = quantity ?: 0,
            details = details ?: "",
            isForChildren = isForChildren,
        )
    }
}
