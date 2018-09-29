package com.assel.footbalapp

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.assel.footbalapp.activity.scheduleDetail.ScheduleDetailViewModel
import com.assel.footbalapp.activity.teamDetail.TeamDetailViewModel

class IntentVmFactory(private val application: Application, private val intent: Intent): ViewModelProvider.NewInstanceFactory()  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ScheduleDetailViewModel::class.java -> ScheduleDetailViewModel(intent, application)
            TeamDetailViewModel::class.java -> TeamDetailViewModel(intent, application)
            else -> throw IllegalArgumentException("unregistered view model: ${modelClass.name}")
        } as T
    }

}
