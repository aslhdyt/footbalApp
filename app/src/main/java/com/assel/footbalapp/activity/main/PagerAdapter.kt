package com.assel.footbalapp.activity.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = when (position) {
        0 -> ScheduleFragment()
        1 -> TeamFragment()
        2 -> OldFragment.newInstance(OldFragment.TYPE_FAVOURITE)
        else -> throw NullPointerException()
    }

    override fun getCount() = tabCount
}