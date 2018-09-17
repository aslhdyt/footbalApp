package com.assel.footbalapp.activity.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = when (position) {
        0 -> ScheduleFragment.newInstance(ScheduleFragment.TYPE_LAST_EVENT)
        1 -> ScheduleFragment.newInstance(ScheduleFragment.TYPE_NEXT_EVENT)
        2 -> ScheduleFragment.newInstance(ScheduleFragment.TYPE_FAVOURITE)
        else -> throw NullPointerException()
    }

    override fun getCount() = tabCount
}