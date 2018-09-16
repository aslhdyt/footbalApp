package com.assel.footbalapp.activity.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.assel.footbalapp.App
import com.assel.footbalapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: DetailViewModel
    lateinit var idlingResource: CountingIdlingResource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        idlingResource = (application as App).idlingResource
        viewModel = ViewModelProviders.of(this, DetailViewModel.Factory(application, intent)).get(DetailViewModel::class.java)

        tvEventDate.text = viewModel.event.dateEvent
        tvHomeName.text = viewModel.event.strHomeTeam
        tvAwayName.text = viewModel.event.strAwayTeam

        tvVs.text = "${viewModel.event.intHomeScore ?: ""} VS ${viewModel.event.intAwayScore?: ""}"

        idlingResource.increment()
        viewModel.homeTeam.observe(this, Observer {
            if (it != null) {
                Picasso.get().load(it.strTeamBadge).into(ivHome)
            }
            idlingResource.decrement()
        })
        idlingResource.increment()
        viewModel.awayTeam.observe(this, Observer {
            if (it != null) {
                Picasso.get().load(it.strTeamBadge).into(ivAway)
            }
            idlingResource.decrement()
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favourite, menu)
        idlingResource.increment()
        viewModel.isFavourite.observe(this, Observer {
            val item = menu.findItem(R.id.favourite)
            if (it == true) {
                item.icon = ContextCompat.getDrawable(this, android.R.drawable.star_big_on)
            } else {
                item.icon = ContextCompat.getDrawable(this, android.R.drawable.star_big_off)
            }
            idlingResource.decrement()
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.favourite -> {
                idlingResource.increment()
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
}
