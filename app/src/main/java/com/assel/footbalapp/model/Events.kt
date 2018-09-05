package com.assel.footbalapp.model

import com.google.gson.annotations.SerializedName

data class Events(
        @SerializedName("events") val events: List<Event>?
)