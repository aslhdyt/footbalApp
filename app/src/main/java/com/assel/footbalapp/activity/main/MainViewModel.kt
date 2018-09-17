package com.assel.footbalapp.activity.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.assel.footbalapp.App
import com.assel.footbalapp.liveData.AppLiveData
import com.assel.footbalapp.liveData.FavouriteLD
import com.assel.footbalapp.liveData.LeagueEventLD
import com.assel.footbalapp.model.League

class MainViewModel(application: Application): AndroidViewModel(application) {
    val allLeague = AppLiveData<List<League>>(application as App)
    var nextEvent = LeagueEventLD(application as App, true)
    var lastEvent = LeagueEventLD(application as App, false)
    var favouriteEvent = FavouriteLD(application as App, this)

}