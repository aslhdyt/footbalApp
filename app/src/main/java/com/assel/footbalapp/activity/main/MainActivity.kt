package com.assel.footbalapp.activity.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        pager.adapter = PagerAdapter(supportFragmentManager, 3)

        bnv.setOnNavigationItemSelectedListener {
            pager.currentItem = when (it.itemId) {
                R.id.action_schedule -> 0
                R.id.action_team -> 1
                R.id.action_favourite -> 2
                else -> 0
            }
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val menuItem = menu.findItem(R.id.search)
        menuItem.setOnMenuItemClickListener {
            val currentPage = when (pager.currentItem) {
                0 -> AppConstant.PAGE_SCHEDULE
                1 -> AppConstant.PAGE_TEAMS
                else -> -1
            }
            startActivity<SearchActivity>(AppConstant.EXTRA_SEARCH to currentPage)
            true
        }
        return true
    }
}
