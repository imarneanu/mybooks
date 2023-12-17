package com.imarneanu.mybooks.domain.model

data class Book(
    val id: Int,
    val name: String,
    val author: String,
    val publication: String,
    val quantity: Int,
    val details: String,
    val isForChildren: Boolean = false,
)
