package com.assel.footbalapp.database

import android.arch.persistence.room.TypeConverter
import com.assel.footbalapp.model.Player
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverters {
    @TypeConverter
    fun playerListToString(list: List<Player>?): String? {
        return Gson().toJson(list, object : TypeToken<List<Player>?>(){}.type)
    }
    @TypeConverter
    fun stringToPlayerList(string: String?): List<Player>? {
        return Gson().fromJson(string, object : TypeToken<List<Player>?>(){}.type)
    }

}