package com.assel.footbalapp.liveData

import android.app.Application
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.Events
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueEventLD(application: Application, isNextEvent: Boolean, id: Int): AppLiveData<List<Event>>(application) {
    override val call =
        if (isNextEvent) client.nextEventLeague(id)
        else client.lastEventLeague(id)

    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) {
            call.enqueue(object: Callback<Events> {
                override fun onResponse(call: Call<Events>, response: Response<Events>) {
                    value = response.body()?.events
                }

                override fun onFailure(call: Call<Events>, t: Throwable) {
                    t.printStackTrace()
                    value = null
                }
            })

        }
    }

    override fun onInactive() {
        if (call.isExecuted) call.cancel()
        super.onInactive()
    }
}
