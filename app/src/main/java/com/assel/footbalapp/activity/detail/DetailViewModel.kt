package com.assel.footbalapp.activity.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.assel.footbalapp.App
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.database.DatabaseConst
import com.assel.footbalapp.database.database
import com.assel.footbalapp.liveData.IsFavouriteLD
import com.assel.footbalapp.liveData.TeamDetailLD
import com.assel.footbalapp.model.Event
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.replaceOrThrow

class DetailViewModel(application: Application, intent: Intent): AndroidViewModel(application) {
    val event = intent.getParcelableExtra<Event>(AppConstant.EXTRA_EVENT)
    val homeTeam = TeamDetailLD(application as App, event.idHomeTeam ?: -1)
    val awayTeam = TeamDetailLD(application as App, event.idAwayTeam ?: -1)
    val isFavourite = IsFavouriteLD(application as App, event.idEvent?.toInt() ?: -1)

    fun toggleFavourite(callback: (isAdded:Boolean) -> Unit) {
        val eventId = event.idEvent?.toIntOrNull()
        if (eventId != null) {
            getApplication<Application>().database.use {
                val favo = isFavourite.value
                if (favo == false) {
                    val ins = replaceOrThrow(DatabaseConst.TABLE_FAVOURITE,
                            DatabaseConst.EVENT_ID to eventId)
                    if (ins > 0L) {
                        callback(true)
                    } else throw Exception("failed to insert")
                } else {
                    val del = delete(DatabaseConst.TABLE_FAVOURITE, "(${DatabaseConst.EVENT_ID}= {id})",
                            "id" to eventId)
                    if (del > 0L) callback(false)
                    else throw Exception("failed to delete")
                }
            }
        } else {
            throw NullPointerException("null eventId")
        }

    }

    class Factory(private val application: Application, private val intent: Intent): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(application, intent) as T
        }
    }
}