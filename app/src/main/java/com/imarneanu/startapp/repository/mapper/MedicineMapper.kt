package com.imarneanu.startapp.repository.mapper

import com.imarneanu.startapp.domain.model.Medicine
import com.imarneanu.startapp.repository.medicine.database.DbMedicine

interface MedicineMapper {

    fun toMeds(dbMedicines: List<DbMedicine>): List<Medicine>

    fun toDbMedicine(medicine: Medicine): DbMedicine
}
