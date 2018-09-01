package com.assel.footbalapp.restApi

import com.assel.footbalapp.model.LeagueEvent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {


    @GET("/eventsnextleague.php?")
    fun getEventsByLeagueId(@Query("id") id: Int) : Call<LeagueEvent>
}