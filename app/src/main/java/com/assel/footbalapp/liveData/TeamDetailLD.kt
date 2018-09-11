package com.assel.footbalapp.liveData

import android.arch.lifecycle.LiveData
import com.assel.footbalapp.model.Team
import com.assel.footbalapp.model.Teams
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailLD(id:Int): LiveData<Team>() {
    private val call = RestClient.getInstance()
            .create(Endpoint::class.java).getTeamDetailsById(id)
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