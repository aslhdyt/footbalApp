package com.assel.footbalapp.restApi

import com.assel.footbalapp.model.LeagueEvent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("eventsday.php?s=Soccer")
    fun getSoccerEventsByDate(@Query("d") d: String) : Call<LeagueEvent>
}