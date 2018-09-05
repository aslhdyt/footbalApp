package com.assel.footbalapp.activity.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.liveData.TeamDetailLD
import com.assel.footbalapp.model.Event

class DetailViewModel(application: Application, intent: Intent): AndroidViewModel(application) {
    val event = intent.getParcelableExtra<Event>(AppConstant.EXTRA_EVENT)
    val homeTeam = TeamDetailLD(event.idHomeTeam ?: -1)
    val awayTeam = TeamDetailLD(event.idAwayTeam ?: -1)

    class Factory(private val application: Application, private val intent: Intent): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(application, intent) as T
        }
    }
}