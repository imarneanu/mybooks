package com.imarneanu.startapp.repository.item.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Item",
)
data class DbItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)
