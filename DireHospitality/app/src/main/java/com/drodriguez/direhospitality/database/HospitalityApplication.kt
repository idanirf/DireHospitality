package com.drodriguez.direhospitality.database

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/*
class HospitalityApplication: Application() {
    companion object {
        lateinit var database: HospitalityDatabase
    }

    override fun onCreate() {
        super.onCreate()

        //variable para modificar la basedatos donde se indicara la modificacion
        val MIGRATIO_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                //indicamos la modificicacion y que ocurre con las filas existentes
                database.execSQL("ALTER TABLE StoreEntity ADD COLUMN photoUrl TEXT NOT NULL DEFAULT ''")
            }
        }

        //construimos la database
        database = Room.databaseBuilder(this,
            HospitalityDatabase::class.java,
            "HospitalityDatabase")
            .addMigrations(MIGRATIO_1_2)
            .build()
    }
}
*/
