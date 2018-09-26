package com.assel.footbalapp.activity.teamDetail

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.assel.footbalapp.IntentVmFactory
import com.assel.footbalapp.R

class TeamDetailActivity : AppCompatActivity() {

    lateinit var viewModel: TeamDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        viewModel = ViewModelProviders.of(this, IntentVmFactory(application, intent)).get(TeamDetailViewModel::class.java)
    }
}
