package com.assel.footbalapp.restApi

import com.assel.footbalapp.model.Events
import com.assel.footbalapp.model.Teams
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("eventsday.php?s=Soccer")
    fun getSoccerEventsByDate(@Query("d") d: String) : Call<Events>

    @GET("lookupteam.php")
    fun getTeamDetailsById(@Query("id") id: Int): Call<Teams>


}