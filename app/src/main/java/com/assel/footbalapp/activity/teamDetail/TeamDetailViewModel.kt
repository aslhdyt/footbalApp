package com.assel.footbalapp.activity.teamDetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Intent
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.liveData.PlayersLD
import com.assel.footbalapp.model.Team

class TeamDetailViewModel(intent: Intent, application: Application): AndroidViewModel(application) {
    val team = intent.getParcelableExtra<Team>(AppConstant.EXTRA_TEAM)
    val playersData = PlayersLD(team.idTeam?.toIntOrNull() ?: -1, application)
}