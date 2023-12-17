package com.imarneanu.mybooks.repository.book.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Book",
)
data class DbBook(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val author: String?,
    val publication: String?,
    val quantity: Int?,
    val details: String?,
    val isForChildren: Boolean = false,
)
