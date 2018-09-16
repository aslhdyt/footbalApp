package com.assel.footbalapp.liveData

import android.arch.lifecycle.LiveData
import com.assel.footbalapp.App
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.Events
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueEventLD(val application: App, isNextEvent: Boolean): LiveData<List<Event>>() {
    private val call = {
        val client = RestClient.getInstance().create(Endpoint::class.java)
        if (isNextEvent) client.nextEventLeague()
        else client.lastEventLeague()
    } ()


    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) {
            application.idlingResource.increment()
            call.enqueue(object: Callback<Events> {
                override fun onResponse(call: Call<Events>, response: Response<Events>) {
                    value = response.body()?.events
                    application.idlingResource.decrement()
                }

                override fun onFailure(call: Call<Events>, t: Throwable) {
                    t.printStackTrace()
                    value = null
                    application.idlingResource.decrement()
                }
            })

        }
    }

    override fun onInactive() {
        if (call.isExecuted) call.cancel()
        super.onInactive()
    }
}
