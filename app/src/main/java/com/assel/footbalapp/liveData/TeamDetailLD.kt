package com.assel.footbalapp.liveData

import android.app.Application
import com.assel.footbalapp.App
import com.assel.footbalapp.model.Team
import com.assel.footbalapp.model.Teams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TeamDetailLD(application: Application, id:Int): AppLiveData<Team>(application as App) {
    override val call = client.getTeamDetailsById(id)
    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) call.enqueue(object: Callback<Teams> {
            override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                value = response.body()?.teams?.get(0)
            }

            override fun onFailure(call: Call<Teams>, t: Throwable) {
                t.printStackTrace()
                value = null
            }
        })
    }

    override fun onInactive() {
        super.onInactive()
        if (call.isExecuted) call.cancel()
    }
}