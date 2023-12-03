package com.imarneanu.startapp.domain.source

import com.imarneanu.startapp.domain.model.Medicine
import com.imarneanu.startapp.repository.medicine.repository.MedicineRepository
import com.imarneanu.startapp.repository.mapper.MedicineMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MedicineSourceImpl(
    private val medicineRepository: MedicineRepository,
    private val medicineMapper: MedicineMapper
) : MedicineSource {

    private val meds = medicineRepository.meds().map(medicineMapper::toMeds)

    override suspend fun insertMedicine(medicine: Medicine) =
        medicineRepository.insertMedicine(medicineMapper.toDbMedicine(medicine))

    override suspend fun deleteMedicine(medicine: Medicine) =
        medicineRepository.deleteMedicine(medicineMapper.toDbMedicine(medicine))

    override suspend fun deleteAll() = medicineRepository.deleteAll()

    override fun meds(): Flow<List<Medicine>> = meds
}
