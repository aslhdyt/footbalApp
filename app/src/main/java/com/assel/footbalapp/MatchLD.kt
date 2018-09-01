package com.assel.footbalapp

import android.arch.lifecycle.LiveData
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.LeagueEvent
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchLD: LiveData<List<Event>>() {
    override fun onActive() {

        super.onActive()
        val api = RetrofitClient.getInstance()
        val call = api.create(Endpoint::class.java).getEventsByLeagueId(4328)
        call.enqueue(object: Callback<LeagueEvent> {
            override fun onResponse(call: Call<LeagueEvent>, response: Response<LeagueEvent>) {
                if (response.isSuccessful) {
                    value = response.body()?.events
                }else {
                    value = null
                }
            }

            override fun onFailure(call: Call<LeagueEvent>, t: Throwable) {
                t.printStackTrace()
                value = null
            }
        })
    }
}