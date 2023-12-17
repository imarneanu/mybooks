package com.imarneanu.mybooks.repository.book.repository

import com.imarneanu.mybooks.repository.book.database.BookDao
import com.imarneanu.mybooks.repository.book.database.DbBook
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(private val bookDao: BookDao) : BookRepository {

    override suspend fun insertBook(dbBook: DbBook) =
        bookDao.insertOrUpdate(dbBook)

    override suspend fun deleteBook(dbBook: DbBook) = bookDao.delete(dbBook)

    override suspend fun deleteAll() = bookDao.deleteAll()

    override fun books(): Flow<List<DbBook>> = bookDao.getAll()
}
