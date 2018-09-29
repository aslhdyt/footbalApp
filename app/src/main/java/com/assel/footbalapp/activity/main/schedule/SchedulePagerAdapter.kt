package com.assel.footbalapp.activity.main.schedule

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.customView.RecyclerFragment

class SchedulePagerAdapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = when (position) {
        0 -> RecyclerFragment.newInstance(AppConstant.TYPE_LAST_EVENT)
        1 -> RecyclerFragment.newInstance(AppConstant.TYPE_NEXT_EVENT)
        else -> throw NullPointerException()
    }

    override fun getCount() = tabCount
}