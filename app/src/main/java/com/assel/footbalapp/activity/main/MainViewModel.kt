package com.assel.footbalapp.activity.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.assel.footbalapp.liveData.LeagueEventLD
import com.assel.footbalapp.model.Event

class MainViewModel: ViewModel() {
    var nextEvent: LiveData<List<Event>> = LeagueEventLD(true)
    var lastEvent: LiveData<List<Event>> = LeagueEventLD(false)
}