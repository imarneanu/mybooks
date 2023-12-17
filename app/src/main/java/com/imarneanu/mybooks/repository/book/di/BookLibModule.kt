package com.imarneanu.mybooks.repository.book.di

import com.imarneanu.mybooks.domain.source.BookSource
import com.imarneanu.mybooks.domain.source.BookSourceImpl
import com.imarneanu.mybooks.domain.usecase.DeleteBook
import com.imarneanu.mybooks.domain.usecase.InsertBook
import com.imarneanu.mybooks.domain.usecase.QueryBook
import com.imarneanu.mybooks.repository.book.repository.BookRepository
import com.imarneanu.mybooks.repository.book.repository.BookRepositoryImpl
import com.imarneanu.mybooks.repository.database.MyDatabase
import com.imarneanu.mybooks.repository.mapper.BookMapper
import com.imarneanu.mybooks.repository.mapper.BookMapperImpl
import org.koin.dsl.module

val bookLibModule = module {
    single { get<MyDatabase>().bookDao() }

    single<BookMapper> { BookMapperImpl() }
    single<BookRepository> { BookRepositoryImpl(get()) }
    single<BookSource> { BookSourceImpl(get(), get()) }

    single { DeleteBook(get()) }
    single { InsertBook(get()) }
    single { QueryBook(get()) }
}
