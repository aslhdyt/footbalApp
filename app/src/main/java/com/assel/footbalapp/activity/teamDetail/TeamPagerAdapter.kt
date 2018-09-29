package com.assel.footbalapp.activity.teamDetail

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.assel.footbalapp.activity.teamDetail.overview.TeamOverviewFragment
import com.assel.footbalapp.activity.teamDetail.players.PlayerFragment

class TeamPagerAdapter(private val tabCount: Int, fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = when (position) {
        0 -> TeamOverviewFragment()
        1 -> PlayerFragment()
        else -> throw NullPointerException()
    }

    override fun getCount() = tabCount
}