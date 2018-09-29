package com.assel.footbalapp.activity.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.main.schedule.ScheduleRecyclerAdapter
import com.assel.footbalapp.activity.main.team.TeamRecylerAdapter
import com.assel.footbalapp.activity.scheduleDetail.ScheduleDetailActivity
import com.assel.footbalapp.activity.teamDetail.TeamDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onPageChangeListener


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        pager.adapter = PagerAdapter(supportFragmentManager, 3)

        pager.onPageChangeListener {
            onPageSelected {
                bnv.selectedItemId = when (it) {
                    0 -> R.id.action_schedule
                    1 -> R.id.action_team
                    2 -> R.id.action_favourite
                    else -> throw IllegalStateException("unregistered page")
                }
            }
        }


        bnv.setOnNavigationItemSelectedListener {
            pager.currentItem = when (it.itemId) {
                R.id.action_schedule -> 0
                R.id.action_team -> 1
                R.id.action_favourite -> 2
                else -> 0
            }
            //hide search button
            if (it.itemId == R.id.action_favourite) {
                hideMenuFlag = true
                invalidateOptionsMenu()
            } else if (hideMenuFlag) {
                hideMenuFlag = false
                invalidateOptionsMenu()
            }

            true
        }

        rvSearch.layoutManager = LinearLayoutManager(this)

    }
    var hideMenuFlag = false
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (hideMenuFlag) return true
        menuInflater.inflate(R.menu.menu_main,menu)
        val searchMenu = menu.findItem(R.id.search)

        val searchView = searchMenu.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { return true }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    toggleSearch(false)
                } else {
                    toggleSearch(true)
                    when(bnv.selectedItemId) {
                        R.id.action_schedule -> {
                            rvSearch.adapter = ScheduleRecyclerAdapter(listOf()) {
                                startActivity<ScheduleDetailActivity>(AppConstant.EXTRA_EVENT to it)
                            }
                            viewModel.searchTeam.removeObservers(this@MainActivity)
                            viewModel.searchEvent.observe(this@MainActivity, Observer { events ->
                                (rvSearch.adapter as ScheduleRecyclerAdapter).events = events ?: listOf()
                                rvSearch.adapter.notifyDataSetChanged()
                                pbSearch.visibility = View.GONE
                            })
                        }
                        R.id.action_team -> {
                            rvSearch.adapter = TeamRecylerAdapter(listOf()) {
                                startActivity<TeamDetailActivity>(AppConstant.EXTRA_TEAM to it)
                            }
                            viewModel.searchEvent.removeObservers(this@MainActivity)
                            viewModel.searchTeam.observe(this@MainActivity, Observer { teams ->
                                (rvSearch.adapter as TeamRecylerAdapter).teams = teams ?: listOf()
                                rvSearch.adapter.notifyDataSetChanged()
                                pbSearch.visibility = View.GONE
                            })
                        }
                    }
                }
                viewModel.searchQuery.postValue(newText)
                return true
            }

        })
        return true
    }

    fun toggleSearch(show: Boolean) {
        if (show) {
            pbSearch.visibility = View.VISIBLE
            rvSearch.visibility = View.VISIBLE
            pager.visibility = View.GONE
            bnv.visibility = View.GONE
        } else {
            rvSearch.visibility = View.GONE
            pbSearch.visibility = View.GONE
            pager.visibility = View.VISIBLE
            bnv.visibility = View.VISIBLE
        }
    }

}
