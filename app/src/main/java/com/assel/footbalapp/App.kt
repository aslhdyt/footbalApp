package com.assel.footbalapp

import android.app.Application
import android.support.test.espresso.idling.CountingIdlingResource

class App: Application() {
    val idlingResource =  CountingIdlingResource("app loading")
}

