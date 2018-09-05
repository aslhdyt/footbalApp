package com.assel.footbalapp.activity.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val event = intent.getParcelableExtra<Event>(AppConstant.EXTRA_EVENT)
        tvEventDate.text = event.dateEvent

    }
}
