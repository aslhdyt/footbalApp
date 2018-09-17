package com.assel.footbalapp.model

import com.google.gson.annotations.SerializedName

data class Leagues(
        @SerializedName("leagues") val leagues: List<League>?
)