package com.assel.footbalapp.activity.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabPagerAdapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = when (position) {
        0 -> RecyclerFragment.newInstance(OldFragment.TYPE_LAST_EVENT)
        1 -> RecyclerFragment.newInstance(OldFragment.TYPE_NEXT_EVENT)
        else -> throw NullPointerException()
    }

    override fun getCount() = tabCount
}