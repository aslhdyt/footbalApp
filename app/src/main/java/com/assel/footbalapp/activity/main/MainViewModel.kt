package com.assel.footbalapp.activity.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.assel.footbalapp.App
import com.assel.footbalapp.liveData.FavouriteLD
import com.assel.footbalapp.liveData.LeagueEventLD

class MainViewModel(application: Application): AndroidViewModel(application) {
    var nextEvent = LeagueEventLD(application as App, true)
    var lastEvent = LeagueEventLD(application as App, false)
    var favouriteEvent = FavouriteLD(application as App, this)

}