package com.assel.footbalapp.liveData

import android.arch.lifecycle.MutableLiveData
import com.assel.footbalapp.database.AppDatabaseOpenHelper
import com.assel.footbalapp.database.DatabaseConst
import org.jetbrains.anko.db.IntParser
import org.jetbrains.anko.db.parseOpt
import org.jetbrains.anko.db.select

class IsFavouriteLD(val database: AppDatabaseOpenHelper, val eventId: Int): MutableLiveData<Boolean>() {
    override fun onActive() {
        database.use {
            select(DatabaseConst.TABLE_FAVOURITE, DatabaseConst.EVENT_ID)
                    .whereArgs("(${DatabaseConst.EVENT_ID} = {id})","id" to eventId)
                    .exec {
                        val result = parseOpt(IntParser) ?: -1
                        value = result != -1
                    }
        }
    }
}