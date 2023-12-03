package com.imarneanu.startapp.repository.medicine.di

import com.imarneanu.startapp.domain.source.MedicineSource
import com.imarneanu.startapp.domain.source.MedicineSourceImpl
import com.imarneanu.startapp.domain.usecase.DeleteMedicine
import com.imarneanu.startapp.domain.usecase.DeleteAllMeds
import com.imarneanu.startapp.domain.usecase.InsertMedicine
import com.imarneanu.startapp.domain.usecase.QueryMedicine
import com.imarneanu.startapp.repository.database.MyMedsDatabase
import com.imarneanu.startapp.repository.medicine.repository.MedicineRepository
import com.imarneanu.startapp.repository.medicine.repository.MedicineRepositoryImpl
import com.imarneanu.startapp.repository.mapper.MedicineMapper
import com.imarneanu.startapp.repository.mapper.MedicineMapperImpl
import org.koin.dsl.module

val medicineLibModule = module {
    single { get<MyMedsDatabase>().medicineDao() }

    single<MedicineMapper> { MedicineMapperImpl() }
    single<MedicineRepository> { MedicineRepositoryImpl(get()) }
    single<MedicineSource> { MedicineSourceImpl(get(), get()) }

    single { DeleteMedicine(get()) }
    single { DeleteAllMeds(get()) }
    single { InsertMedicine(get()) }
    single { QueryMedicine(get()) }
}
