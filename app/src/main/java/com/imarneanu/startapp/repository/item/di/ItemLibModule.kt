package com.imarneanu.startapp.repository.item.di

import com.imarneanu.startapp.domain.source.ItemSource
import com.imarneanu.startapp.domain.source.ItemSourceImpl
import com.imarneanu.startapp.domain.usecase.DeleteItem
import com.imarneanu.startapp.domain.usecase.InsertItem
import com.imarneanu.startapp.domain.usecase.QueryItem
import com.imarneanu.startapp.repository.database.MyDatabase
import com.imarneanu.startapp.repository.item.repository.ItemRepository
import com.imarneanu.startapp.repository.item.repository.ItemRepositoryImpl
import com.imarneanu.startapp.repository.mapper.ItemMapper
import com.imarneanu.startapp.repository.mapper.ItemMapperImpl
import org.koin.dsl.module

val itemLibModule = module {
    single { get<MyDatabase>().itemDao() }

    single<ItemMapper> { ItemMapperImpl() }
    single<ItemRepository> { ItemRepositoryImpl(get()) }
    single<ItemSource> { ItemSourceImpl(get(), get()) }

    single { DeleteItem(get()) }
    single { InsertItem(get()) }
    single { QueryItem(get()) }
}
