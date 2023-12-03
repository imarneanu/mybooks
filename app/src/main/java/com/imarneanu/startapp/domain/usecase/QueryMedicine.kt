package com.imarneanu.startapp.domain.usecase

import com.imarneanu.startapp.core.usecase.QueryUseCase
import com.imarneanu.startapp.domain.model.Medicine
import com.imarneanu.startapp.domain.source.MedicineSource
import kotlinx.coroutines.flow.Flow

class QueryMedicine(private val medicineSource: MedicineSource): QueryUseCase<List<Medicine>> {
    override fun invoke(): Flow<List<Medicine>> = medicineSource.meds()
}
