package com.imarneanu.startapp.domain.usecase

import com.imarneanu.startapp.core.usecase.CommandUseCase
import com.imarneanu.startapp.domain.source.MedicineSource

class DeleteAllMeds(private val medicineSource: MedicineSource): CommandUseCase {
    override suspend fun invoke() = medicineSource.deleteAll()
}
