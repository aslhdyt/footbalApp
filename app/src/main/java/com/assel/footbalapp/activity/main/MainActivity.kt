package com.assel.footbalapp.activity.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.assel.footbalapp.R
import kotlinx.android.synthetic.main.activity_main.*


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
//        val tabLayout = find<TabLayout>(MainUI.Ids.tabLayout).apply {
//            addTab(newTab().setText("Last Event"))
//            addTab(newTab().setText("Next Event"))
//            addTab(newTab().setText("Favourite"))
//            tabGravity = TabLayout.GRAVITY_FILL
//        }

//        val viewPager = find<ViewPager>(MainUI.Ids.viewPager)
//        viewPager.adapter = TabFragment.Adapter(supportFragmentManager, tabLayout.tabCount)
//
//        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//        tabLayout.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                viewPager.currentItem = tab?.position ?: 0
//            }
//        })
    }
}
