package com.assel.footbalapp

import com.assel.footbalapp.model.Events
import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RestClient
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import java.util.*

class ApiRepoTest {

    lateinit var call: Call<Events>

    var isNextEvent: Boolean = Random().nextBoolean()

    @Before
    fun setUp() {
        call = {
            val client = RestClient.getInstance().create(Endpoint::class.java)
            if (isNextEvent) client.nextEventLeague()
            else client.lastEventLeague()
        } ()
    }
    @Test
    fun testEmptyList() {
        val result = call.execute().body()?.events

        println("result size: ${result?.size}, isNextEvent: $isNextEvent")
        assert(result?.size!! > 0)
    }
}