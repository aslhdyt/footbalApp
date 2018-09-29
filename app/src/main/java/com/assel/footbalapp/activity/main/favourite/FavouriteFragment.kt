package com.assel.footbalapp.activity.main.favourite

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.main.MainActivity
import com.assel.footbalapp.activity.main.MainViewModel
import kotlinx.android.synthetic.main.favourite_layout.view.*

class FavouriteFragment: Fragment() {


    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as MainActivity
        viewModel = context.viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.favourite_layout, container, false).apply {
            viewPager.adapter = FavouritePagerAdapter(childFragmentManager, tlFavourite.tabCount)
            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlFavourite))
            tlFavourite.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewPager.currentItem = tab?.position ?: 0
                }
            })
        }
    }
}
