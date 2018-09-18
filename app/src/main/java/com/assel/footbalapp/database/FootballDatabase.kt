package com.assel.footbalapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.assel.footbalapp.model.Event

@Database(entities = [Event::class], version = 1)
abstract class FootballDatabase: RoomDatabase() {
    abstract fun dao(): FootbalDao
    companion object {
        private var INSTANCE: FootballDatabase? = null
        fun getInstance(context: Context): FootballDatabase{
            if (INSTANCE == null) {
                synchronized(FootballDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            FootballDatabase::class.java, "football.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}