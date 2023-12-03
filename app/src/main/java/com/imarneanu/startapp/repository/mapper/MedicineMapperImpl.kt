package com.imarneanu.startapp.repository.mapper

import com.imarneanu.startapp.domain.model.Medicine
import com.imarneanu.startapp.repository.medicine.database.DbMedicine

class MedicineMapperImpl : MedicineMapper {

    override fun toMeds(dbMedicines: List<DbMedicine>): List<Medicine> = dbMedicines.map(::toMedicine)

    override fun toDbMedicine(medicine: Medicine): DbMedicine = with(medicine) {
        DbMedicine(
            name = name,
            expirationDate = expirationDate,
            quantity = quantity,
            quantityLabel = quantityLabel,
        )
    }

    private fun toMedicine(dbMedicine: DbMedicine) = with(dbMedicine) {
        Medicine(
            name = name,
            expirationDate = expirationDate,
            quantity = quantity,
            quantityLabel = quantityLabel,
        )
    }
}
