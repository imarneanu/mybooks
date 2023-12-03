package com.imarneanu.startapp.domain.model

data class Medicine(
    val name: String,
    val expirationDate: String? = null,
    val quantity: Double = 0.0,
    val quantityLabel: String? = null,
)
