package com.assel.footbalapp.model

import com.google.gson.annotations.SerializedName

data class LeagueEvent(
        @SerializedName("events") val events: List<Event>?
)