package com.imarneanu.startapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.imarneanu.startapp.repository.medicine.database.DbMedicine
import com.imarneanu.startapp.repository.medicine.database.MedicineDao

const val DB_VERSION_OLD = 1
const val DB_VERSION = 1

@Database(
    entities = [
        DbMedicine::class,
    ],
    version = DB_VERSION
)
abstract class MyMedsDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {
        const val DATABASE_NAME = "my_meds"

        val extendDatabaseMigration = object : Migration(DB_VERSION_OLD, DB_VERSION) {
            override fun migrate(database: SupportSQLiteDatabase) = with(database) {
                execSQL("DROP TABLE IF EXISTS Medicine_old")
                execSQL("ALTER TABLE Medicine RENAME TO Medicine_old")
                execSQL("CREATE TABLE IF NOT EXISTS `Medicine` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))")
                execSQL("INSERT INTO Medicine (id, name) SELECT id, name FROM Medicine")
                execSQL("DROP TABLE IF EXISTS Medicine_old")
            }
        }
    }
}
