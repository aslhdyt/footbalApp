package com.assel.footbalapp.activity.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.assel.footbalapp.App
import com.assel.footbalapp.liveData.LeagueEventLD
import com.assel.footbalapp.liveData.LeagueLD
import com.assel.footbalapp.model.Event

class MainViewModel(application: Application): AndroidViewModel(application) {
    val allLeague = LeagueLD(application as App)

    val currentSelectedLegue = MutableLiveData<Int>()

    var nextEvent = Transformations.switchMap<Int, List<Event>>(currentSelectedLegue) {
        LeagueEventLD(application as App, true, it)
    }
    var lastEvent = Transformations.switchMap<Int, List<Event>>(currentSelectedLegue) {
        LeagueEventLD(application as App, false, it)
    }
}