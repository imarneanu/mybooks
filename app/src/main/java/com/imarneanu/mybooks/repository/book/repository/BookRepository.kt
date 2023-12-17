package com.imarneanu.mybooks.repository.book.repository

import com.imarneanu.mybooks.repository.book.database.DbBook
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun insertBook(dbBook: DbBook)

    suspend fun deleteBook(dbBook: DbBook)
    suspend fun deleteAll()
    fun books(): Flow<List<DbBook>>
}
