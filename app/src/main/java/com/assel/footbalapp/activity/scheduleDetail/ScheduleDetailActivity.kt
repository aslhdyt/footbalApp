package com.assel.footbalapp.activity.scheduleDetail

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.assel.footbalapp.*
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.teamDetail.TeamDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class ScheduleDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ScheduleDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = ViewModelProviders.of(this, IntentVmFactory(application, intent)).get(ScheduleDetailViewModel::class.java)

        tvEventDate.text = viewModel.event.dateEvent
        tvHomeName.text = viewModel.event.strHomeTeam
        tvAwayName.text = viewModel.event.strAwayTeam

        tvVs.text = "${viewModel.event.intHomeScore ?: ""} VS ${viewModel.event.intAwayScore?: ""}"

        viewModel.homeTeam.observe(this, Observer { team ->
            if (team != null) {
                Picasso.get().load(team.strTeamBadge).into(ivHome)
                ivHome.onClick {
                    startActivity<TeamDetailActivity>(AppConstant.EXTRA_TEAM to team)
                }
            }
        })
        viewModel.awayTeam.observe(this, Observer {team ->
            if (team != null) {
                Picasso.get().load(team.strTeamBadge).into(ivAway)
                ivAway.onClick {
                    startActivity<TeamDetailActivity>(AppConstant.EXTRA_TEAM to team)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favourite, menu)
        viewModel.isFavourite.observe(this, Observer {
            val item = menu.findItem(R.id.favourite)
            if (it != null) {
                item.icon = ContextCompat.getDrawable(this, android.R.drawable.star_big_on)
            } else {
                item.icon = ContextCompat.getDrawable(this, android.R.drawable.star_big_off)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.favourite -> {
                application.idlingResource.increment()
                viewModel.toggleFavourite {
                    if (it) {
                        toast("Added to favourite").show()
                    }else {
                        toast("Deleted from favourite").show()
                    }
                    (application as App).idlingResource.decrement()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

