package com.assel.footbalapp.activity.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.assel.footbalapp.App
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.database.FootballDatabase
import com.assel.footbalapp.liveData.TeamDetailLD
import com.assel.footbalapp.model.Event
import org.jetbrains.anko.doAsync

class DetailViewModel(intent: Intent, application: Application): AndroidViewModel(application) {
    val db = FootballDatabase.getInstance(application).dao()
    val event = intent.getParcelableExtra<Event>(AppConstant.EXTRA_EVENT)
    val homeTeam = TeamDetailLD(application as App, event.idHomeTeam ?: -1)
    val awayTeam = TeamDetailLD(application as App, event.idAwayTeam ?: -1)
    val isFavourite = db.selectEventById(event.idEvent?.toInt() ?: 0)

    fun toggleFavourite(callback: (isAdded:Boolean) -> Unit) {
        val favEvent = isFavourite.value
        doAsync {
            if (favEvent == null) {
                db.insertEvent(event)
                callback(true)
            } else {
                db.deleteEventById(favEvent.idEvent?.toInt()?: 0)
                callback(false)
            }
        }

    }


    class Factory(private val application: Application, private val intent: Intent): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(intent, application) as T
        }
    }


}
