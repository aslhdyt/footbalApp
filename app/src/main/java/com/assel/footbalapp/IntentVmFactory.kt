package com.assel.footbalapp

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.assel.footbalapp.activity.detail.DetailViewModel
import com.assel.footbalapp.activity.search.SearchViewModel

class IntentVmFactory(private val application: Application, private val intent: Intent): ViewModelProvider.NewInstanceFactory()  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            SearchViewModel::class.java -> SearchViewModel(intent, application)
            DetailViewModel::class.java -> DetailViewModel(intent, application)
            else -> throw IllegalArgumentException("unregistered view model: ${modelClass.name}")
        } as T
    }

}
