package com.assel.footbalapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.assel.footbalapp.model.Event

@Dao
interface FootbalDao {

    @Insert
    fun insertEvent(event: Event)

    @Query("DELETE from Event WHERE idEvent = :idEvent")
    fun deleteEventById(idEvent: Int)

    @Query("SELECT * FROM Event")
    fun selectAllEvent(): LiveData<List<Event>>

    @Query("SELECT * FROM Event WHERE idEvent = :idEvent")
    fun selectEventById(idEvent: Int) : LiveData<Event?>



}
