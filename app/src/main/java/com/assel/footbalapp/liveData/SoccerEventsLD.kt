package com.assel.footbalapp.liveData

import android.arch.lifecycle.LiveData
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.Events
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoccerEventsLD(date: String): LiveData<List<Event>>() {
    private val call = RetrofitClient.getInstance()
            .create(Endpoint::class.java).getSoccerEventsByDate(date)


    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) call.enqueue(object: Callback<Events> {
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                value = response.body()?.events
            }

            override fun onFailure(call: Call<Events>, t: Throwable) {
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