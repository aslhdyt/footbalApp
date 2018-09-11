package com.assel.footbalapp.activity.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.assel.footbalapp.liveData.FavouriteLD
import com.assel.footbalapp.liveData.LeagueEventLD

class MainViewModel(application: Application): AndroidViewModel(application) {
    var nextEvent = LeagueEventLD(true)
    var lastEvent = LeagueEventLD(false)
    var favouriteEvent = FavouriteLD(application, this)
}