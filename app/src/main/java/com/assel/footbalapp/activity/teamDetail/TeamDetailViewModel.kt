package com.assel.footbalapp.activity.teamDetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Observer
import android.content.Intent
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.database.FootballDatabase
import com.assel.footbalapp.liveData.PlayersLD
import com.assel.footbalapp.model.Player
import com.assel.footbalapp.model.Team
import org.jetbrains.anko.doAsync

class TeamDetailViewModel(intent: Intent, application: Application): AndroidViewModel(application) {
    val db = FootballDatabase.getInstance(application).dao()
    val team = intent.getParcelableExtra<Team>(AppConstant.EXTRA_TEAM)

    val playersData = PlayersLD(team.idTeam.toInt(), application).apply {
        this.observeForever(object : Observer<List<Player>?> {
            override fun onChanged(t: List<Player>?) {
                if (t != null) {
                    team.players = t
                    this@apply.removeObserver(this)
                }
            }

        })
    }
    val isFavourite = db.selectTeamById(team.idTeam.toInt())


    fun toggleFavourite(callback: (isAdded:Boolean) -> Unit) {
        val favTeam = isFavourite.value
        doAsync {
            if (favTeam == null) {
                db.insertTeam(team)
                callback(true)
            } else {
                db.deleteTeamById(favTeam.idTeam.toInt())
                callback(false)
            }
        }

    }
}