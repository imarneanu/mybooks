package com.imarneanu.startapp.domain.usecase

import com.imarneanu.startapp.core.usecase.CommandUseCase1
import com.imarneanu.startapp.domain.model.Medicine
import com.imarneanu.startapp.domain.source.MedicineSource

@Suppress("parameter_name_changed_on_override")
class DeleteMedicine(private val medicineSource: MedicineSource): CommandUseCase1<Medicine> {
    override suspend fun invoke(medicine: Medicine) = medicineSource.deleteMedicine(medicine)
}
