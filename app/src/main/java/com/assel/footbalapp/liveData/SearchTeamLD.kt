package com.assel.footbalapp.liveData

import android.app.Application
import com.assel.footbalapp.App
import com.assel.footbalapp.model.Team
import com.assel.footbalapp.model.Teams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTeamLD(application: Application, it: String) : AppLiveData<List<Team>?>(application as App) {
    override val call: Call<Teams> = client.searchTeamByName(it)

    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) {
            call.enqueue(object: Callback<Teams> {
                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    value = response.body()?.teams
                }

                override fun onFailure(call: Call<Teams>, t: Throwable) {
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
