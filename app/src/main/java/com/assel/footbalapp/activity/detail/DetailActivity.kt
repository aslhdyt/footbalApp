package com.assel.footbalapp.activity.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.assel.footbalapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ViewModelProviders.of(this, DetailViewModel.Factory(application, intent)).get(DetailViewModel::class.java)

        tvEventDate.text = viewModel.event.dateEvent
        tvHomeName.text = viewModel.event.strHomeTeam
        tvAwayName.text = viewModel.event.strAwayTeam

        tvVs.text = "${viewModel.event.intHomeScore ?: ""} VS ${viewModel.event.intAwayScore?: ""}"

        viewModel.homeTeam.observe(this, Observer {
            if (it != null) {
                Picasso.get().load(it.strTeamBadge).into(ivHome)
            }
        })
        viewModel.awayTeam.observe(this, Observer {
            if (it != null) {
                Picasso.get().load(it.strTeamBadge).into(ivAway)
            }
        })
    }
}
