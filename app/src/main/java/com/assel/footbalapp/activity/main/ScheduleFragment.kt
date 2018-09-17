package com.assel.footbalapp.activity.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.detail.DetailActivity
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.recycler_layout.view.*
import kotlinx.android.synthetic.main.tab_layout.view.*
import org.jetbrains.anko.support.v4.startActivity

class ScheduleFragment: Fragment() {


    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as MainActivity
        viewModel = context.viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_layout, container, false).apply {
            viewPager.adapter = TabPagerAdapter(childFragmentManager, tabLayout.tabCount)

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

    companion object {
        fun newInstance(): OldFragment {
            val args = Bundle()
            val fragment = OldFragment()
            fragment.arguments = args
            return fragment
        }
    }


}
