package com.assel.footbalapp.activity.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.assel.footbalapp.liveData.SoccerEventsLD
import com.assel.footbalapp.model.Event
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel: ViewModel() {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentSelectedTime = MutableLiveData<String>().apply { value = dateFormat.format(Date())}
    var eventsLiveData: LiveData<List<Event>> = Transformations.switchMap(currentSelectedTime) {
        SoccerEventsLD(it)
    }


}