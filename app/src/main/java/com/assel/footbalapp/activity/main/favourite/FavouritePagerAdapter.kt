package com.assel.footbalapp.activity.main.favourite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.customView.RecyclerFragment

class FavouritePagerAdapter(supportFragmentManager: FragmentManager, private val tabCount: Int) : FragmentStatePagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RecyclerFragment.newInstance(AppConstant.TYPE_DB_EVENT)
            1 -> RecyclerFragment.newInstance(AppConstant.TYPE_DB_TEAM)
            else -> throw IllegalStateException()
        }
    }

    override fun getCount(): Int = tabCount


}
