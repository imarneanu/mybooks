package com.imarneanu.startapp.domain.usecase

import com.imarneanu.startapp.core.usecase.QueryUseCase
import com.imarneanu.startapp.domain.model.Item
import com.imarneanu.startapp.domain.source.ItemSource
import kotlinx.coroutines.flow.Flow

class QueryItem(private val itemSource: ItemSource): QueryUseCase<List<Item>> {
    override fun invoke(): Flow<List<Item>> = itemSource.items()
}
