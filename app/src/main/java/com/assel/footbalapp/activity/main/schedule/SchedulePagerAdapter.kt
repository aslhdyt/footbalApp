package com.assel.footbalapp.activity.main.schedule

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SchedulePagerAdapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = when (position) {
        0 -> RecyclerFragment.newInstance(RecyclerFragment.TYPE_LAST_EVENT)
        1 -> RecyclerFragment.newInstance(RecyclerFragment.TYPE_NEXT_EVENT)
        else -> throw NullPointerException()
    }

    override fun getCount() = tabCount
}