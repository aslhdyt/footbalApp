package com.assel.footbalapp.activity.teamDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.assel.footbalapp.App
import com.assel.footbalapp.IntentVmFactory
import com.assel.footbalapp.R
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity() {

    lateinit var viewModel: TeamDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        viewModel = ViewModelProviders.of(this, IntentVmFactory(application, intent)).get(TeamDetailViewModel::class.java)


        viewPager.adapter = TeamPagerAdapter(2, supportFragmentManager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position ?: 0
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
                (application as App).idlingResource.increment()
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
