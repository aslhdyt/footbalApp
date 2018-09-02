package com.assel.footbalapp.activity.main

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.assel.footbalapp.SoccerEventsByDate
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUI().setContentView(this)


        val liveData = SoccerEventsByDate("2018-8-8")
        liveData.observe(this, Observer {
            if (it != null) {
                val recyclerView = find<RecyclerView>(MainUI.Ids.recyclerView)
                recyclerView.adapter = MainAdapter(it)
                recyclerView.adapter?.notifyDataSetChanged()
                it.forEach {event ->
                    println("event: $event")
                }
            } else {
                println("null events")
            }
        })
    }
}
