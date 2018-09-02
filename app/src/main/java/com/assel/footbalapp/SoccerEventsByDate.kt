package com.assel.footbalapp

import android.arch.lifecycle.LiveData
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.LeagueEvent
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoccerEventsByDate(date: String): LiveData<List<Event>>() {
    private val call = RetrofitClient.getInstance()
            .create(Endpoint::class.java).getSoccerEventsByDate(date)


    override fun onActive() {

        super.onActive()
        if (!call.isExecuted) call.enqueue(object: Callback<LeagueEvent> {
            override fun onResponse(call: Call<LeagueEvent>, response: Response<LeagueEvent>) {
                value = response.body()?.events
            }

            override fun onFailure(call: Call<LeagueEvent>, t: Throwable) {
                t.printStackTrace()
                value = null
            }
        })
    }

    override fun onInactive() {
        if (call.isExecuted) call.cancel()
        super.onInactive()
    }
}