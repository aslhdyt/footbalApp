package com.assel.footbalapp.restApi

import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.Events
import com.assel.footbalapp.model.Teams
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("eventsnextleague.php?id=4328")
    fun nextEventLeague() : Call<Events>


    @GET("eventspastleague.php?id=4328")
    fun lastEventLeague() : Call<Events>

    @GET("lookupteam.php")
    fun getTeamDetailsById(@Query("id") id: Int): Call<Teams>

    @GET("lookupevent.php")
    fun getEventDetailById(@Query("id") id: Int): Call<Events>

}