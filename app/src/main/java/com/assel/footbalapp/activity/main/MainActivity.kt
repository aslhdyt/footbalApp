package com.assel.footbalapp.activity.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUI().setContentView(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val tabLayout = find<TabLayout>(MainUI.Ids.tabLayout).apply {
            addTab(newTab().setText("Last Event"))
            addTab(newTab().setText("Next Event"))
            tabGravity = TabLayout.GRAVITY_FILL
        }


        val viewPager = find<ViewPager>(MainUI.Ids.viewPager)
        viewPager.adapter = TabFragment.Adapter(supportFragmentManager, tabLayout.tabCount)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position ?: 0
            }

        })

    }
}
