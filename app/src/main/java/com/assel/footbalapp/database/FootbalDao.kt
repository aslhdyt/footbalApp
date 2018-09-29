package com.assel.footbalapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.Team

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

    @Query("SELECT * FROM Team WHERE idTeam = :idTeam")
    fun selectTeamById(idTeam: Int) : LiveData<Team?>

    @Insert
    fun insertTeam(team: Team)

    @Query("DELETE from Team WHERE idTeam = :idTeam")
    fun deleteTeamById(idTeam: Int)

    @Query("SELECT * FROM Team")
    fun selectAllTeam(): LiveData<List<Team>>

    @Query("DELETE from Team")
    fun deleteAllTeam()
    @Query("DELETE from Event")
    fun deleteAllEvent()


}
