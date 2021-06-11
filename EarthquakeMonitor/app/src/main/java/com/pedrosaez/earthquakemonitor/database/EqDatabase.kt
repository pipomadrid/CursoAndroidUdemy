package com.pedrosaez.earthquakemonitor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pedrosaez.earthquakemonitor.Earthquake

@Database(entities = [Earthquake::class], version = 1)
abstract class EqDatabase :RoomDatabase(){
     abstract val eqDao:EqDao

}

private lateinit var INSTANCE: EqDatabase

fun getDatabase(context:Context): EqDatabase {
    synchronized(EqDatabase::class.java) {// esto hace que solo se pueda llamar desde un hilo a la vez

        //creamos un patrón singleton para que INSTANCE solo pueda ser inicializada una vez
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext, EqDatabase::class.java, "earthquake_db").build()
        }
        return INSTANCE
    }


}