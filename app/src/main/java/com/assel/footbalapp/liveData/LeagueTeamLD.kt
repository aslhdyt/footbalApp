package com.assel.footbalapp.liveData

import android.arch.lifecycle.LiveData
import com.assel.footbalapp.App
import com.assel.footbalapp.model.Team
import com.assel.footbalapp.model.Teams
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueTeamLD(val application: App, leagueId: Int): LiveData<List<Team>>() {

    private val call = RestClient.getInstance().create(Endpoint::class.java).getTeamsByLeagueId(leagueId)


    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) {
            application.idlingResource.increment()
            call.enqueue(object: Callback<Teams> {
                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    value = response.body()?.teams
                    application.idlingResource.decrement()
                }

                override fun onFailure(call: Call<Teams>, t: Throwable) {
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
