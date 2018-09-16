package com.assel.footbalapp.activity.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.assel.footbalapp.R
import com.assel.footbalapp.idlingResource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
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
            application.idlingResource.decrement()
        })
        viewModel.awayTeam.observe(this, Observer {
            if (it != null) {
                Picasso.get().load(it.strTeamBadge).into(ivAway)
            }
            application.idlingResource.decrement()
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favourite, menu)
        viewModel.isFavourite.observe(this, Observer {
            val item = menu.findItem(R.id.favourite)
            if (it == true) {
                item.icon = ContextCompat.getDrawable(this, android.R.drawable.star_big_on)
            } else {
                item.icon = ContextCompat.getDrawable(this, android.R.drawable.star_big_off)
            }
            application.idlingResource.decrement()
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
                        viewModel.isFavourite.postValue(true)
                        toast("Added to favourite").show()
                    }else {
                        viewModel.isFavourite.postValue(false)
                        toast("Deleted from favourite").show()
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
