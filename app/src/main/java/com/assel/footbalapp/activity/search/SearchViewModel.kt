package com.assel.footbalapp.activity.search

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.assel.footbalapp.AppConstant.EXTRA_SEARCH

class SearchViewModel(intent: Intent, application: Application): AndroidViewModel(application) {

    private val currentPage = intent.getIntExtra(EXTRA_SEARCH, -1)

    fun doSearch(it: String?) {
        println("do search $currentPage: $it")
    }
}