package com.assel.footbalapp.liveData

import android.arch.lifecycle.LiveData
import android.content.Context
import com.assel.footbalapp.activity.main.MainViewModel
import com.assel.footbalapp.database.DatabaseConst
import com.assel.footbalapp.database.database
import com.assel.footbalapp.model.Event
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RestClient
import org.jetbrains.anko.db.select

class FavouriteLD(val context: Context, val viewModel: MainViewModel): LiveData<List<Event>>() {
    override fun onActive() {
        context.database.use {
            select(DatabaseConst.TABLE_FAVOURITE).exec {
                val dataList = arrayListOf<Event>()
                while (this.moveToNext()) {
                    val eventId = getInt(getColumnIndexOrThrow(DatabaseConst.EVENT_ID))
                    println("eventId: $eventId")

                    val data = viewModel.lastEvent.value?.firstOrNull { it.idEvent?.toIntOrNull() == eventId } ?:
                    viewModel.nextEvent.value?.firstOrNull {it.idEvent?.toIntOrNull() == eventId } ?:
                    {
                        val response  = RestClient.getInstance()
                                .create(Endpoint::class.java)
                                .getEventDetailById(eventId)
                                .execute()
                        if (response.isSuccessful) {
                            response.body()?.events?.get(0)
                        } else null
                    } ()

                    if (data != null) dataList.add(data)
                }
                value = dataList.toList()
            }
        }
    }


    override fun onInactive() {
        context.database.close()
    }

}