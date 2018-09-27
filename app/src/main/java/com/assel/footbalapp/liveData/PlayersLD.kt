package com.assel.footbalapp.liveData

import android.app.Application
import com.assel.footbalapp.App
import com.assel.footbalapp.model.Player
import com.assel.footbalapp.model.Players
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayersLD(teamId:Int, application: Application): AppLiveData<List<Player>?>(application as App) {
    override val call = client.getPlayersByTeamId(teamId)


    override fun onActive() {
        super.onActive()
        if (!call.isExecuted) {
            call.enqueue(object: Callback<Players> {
                override fun onResponse(call: Call<Players>, response: Response<Players>) {
                    value = response.body()?.player
                }

                override fun onFailure(call: Call<Players>, t: Throwable) {
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