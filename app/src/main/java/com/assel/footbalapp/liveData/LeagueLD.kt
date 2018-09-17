package com.assel.footbalapp.liveData

import com.assel.footbalapp.App
import com.assel.footbalapp.model.League
import com.assel.footbalapp.model.Leagues
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueLD(application: App): AppLiveData<List<League>?>(application) {
    override val call: Call<Leagues> = client.getAllLeague()

    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) {
            call.enqueue(object: Callback<Leagues> {
                override fun onResponse(call: Call<Leagues>, response: Response<Leagues>) {
                    value = response.body()?.leagues?.filter { it.strSport == "Soccer" }
                }

                override fun onFailure(call: Call<Leagues>, t: Throwable) {
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