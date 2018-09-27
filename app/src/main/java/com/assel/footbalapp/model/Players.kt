package com.assel.footbalapp.model

import com.google.gson.annotations.SerializedName

data class Players(
        @SerializedName("player") val player: List<Player>?
)