package com.assel.footbalapp

import com.assel.footbalapp.restApi.Endpoint
import com.assel.footbalapp.restApi.RestClient
import org.junit.Before
import org.junit.Test
import java.util.*

class ApiRepoTest {

    private val client = RestClient.getInstance().create(Endpoint::class.java)

    @Before
    fun setUp() {
    }
    @Test
    fun testGetEventAndTeam() {
        val callEvents = {
            if (Random().nextBoolean()) client.nextEventLeague()
            else client.lastEventLeague()
        } ()
        val eventResult = callEvents.execute().body()?.events

        assert(eventResult?.size ?: -1 > 0)
        val randomEvent = eventResult?.get(Random().nextInt(eventResult.lastIndex))
        val callTeam = client.getTeamDetailsById(randomEvent?.idAwayTeam ?: 0)
        val teamResult = callTeam.execute().body()?.teams?.get(0)
        assert(randomEvent?.idAwayTeam == teamResult?.idTeam?.toInt())
    }
}