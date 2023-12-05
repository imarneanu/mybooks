package com.imarneanu.startapp.ui.items

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val itemsModule = module {
    viewModel {
        ItemsViewModel(
            queryItems = get(),
            insertItem = get(),
            deleteItem = get(),
        )
    }
}
