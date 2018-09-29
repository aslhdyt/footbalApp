package com.assel.footbalapp.activity.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.assel.footbalapp.activity.main.favourite.FavouriteFragment
import com.assel.footbalapp.activity.main.schedule.ScheduleFragment
import com.assel.footbalapp.activity.main.team.TeamFragment
import java.lang.IllegalStateException

class PagerAdapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = when (position) {
        0 -> ScheduleFragment()
        1 -> TeamFragment()
        2 -> FavouriteFragment()
        else -> throw IllegalStateException()
    }

    override fun getCount() = tabCount
}