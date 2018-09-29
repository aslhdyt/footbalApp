package com.assel.footbalapp.liveData

import android.app.Application
import android.arch.lifecycle.LiveData
import com.assel.footbalapp.idlingResource
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RestClient
import retrofit2.Call

abstract class AppLiveData<T>(val application: Application): LiveData<T>() {
    val client = RestClient.getInstance().create(Endpoint::class.java)
    abstract val call: Call<*>

    override fun onActive() {
        application.idlingResource.increment()
        super.onActive()
    }

    override fun setValue(value: T?) {
        super.setValue(value)
        application.idlingResource.decrement()
    }
}