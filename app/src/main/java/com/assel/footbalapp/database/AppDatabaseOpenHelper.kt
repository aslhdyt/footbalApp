package com.assel.footbalapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class AppDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 2) {

    companion object {
        private var instance: AppDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabaseOpenHelper {
            if (instance == null) {
                instance = AppDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(DatabaseConst.TABLE_FAVOURITE, true,
                DatabaseConst.EVENT_ID to INTEGER + PRIMARY_KEY + UNIQUE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(DatabaseConst.TABLE_FAVOURITE, true)
    }

}



// Access property for Context
val Context.database: AppDatabaseOpenHelper
    get() = AppDatabaseOpenHelper.getInstance(applicationContext)