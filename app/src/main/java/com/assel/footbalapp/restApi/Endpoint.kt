package com.assel.footbalapp.restApi

import com.assel.footbalapp.model.Events
import com.assel.footbalapp.model.Leagues
import com.assel.footbalapp.model.Teams
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("eventsnextleague.php")
    fun nextEventLeague(@Query("id") id: Int) : Call<Events>


    @GET("eventspastleague.php")
    fun lastEventLeague(@Query("id") id: Int) : Call<Events>

    @GET("lookupteam.php")
    fun getTeamDetailsById(@Query("id") id: Int): Call<Teams>

    @GET("lookupevent.php")
    fun getEventDetailById(@Query("id") id: Int): Call<Events>

    @GET("all_leagues.php?")
    fun getAllLeague(): Call<Leagues>

}