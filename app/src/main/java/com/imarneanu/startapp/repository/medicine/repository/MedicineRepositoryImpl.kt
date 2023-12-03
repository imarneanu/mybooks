package com.imarneanu.startapp.repository.medicine.repository

import com.imarneanu.startapp.repository.medicine.database.DbMedicine
import com.imarneanu.startapp.repository.medicine.database.MedicineDao
import kotlinx.coroutines.flow.Flow

class MedicineRepositoryImpl(private val medicineDao: MedicineDao) : MedicineRepository {

    override suspend fun insertMedicine(dbMedicine: DbMedicine) =
        medicineDao.insertOrUpdate(dbMedicine)

    override suspend fun deleteMedicine(dbMedicine: DbMedicine) = medicineDao.delete(dbMedicine)

    override suspend fun deleteAll() = medicineDao.deleteAll()

    override fun meds(): Flow<List<DbMedicine>> = medicineDao.getAll()
}
