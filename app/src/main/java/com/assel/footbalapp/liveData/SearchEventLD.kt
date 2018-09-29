package com.assel.footbalapp.liveData

import android.app.Application
import com.assel.footbalapp.App
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.model.Events
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchEventLD(application: Application, query: String): AppLiveData<List<Event>?>(application as App) {
    override val call: Call<Events> = client.searchEventByName(query)

    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) {
            call.enqueue(object: Callback<Events> {
                override fun onResponse(call: Call<Events>, response: Response<Events>) {
                    value = response.body()?.event
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