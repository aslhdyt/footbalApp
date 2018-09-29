package com.assel.footbalapp.activity.main.favourite

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.scheduleDetail.ScheduleDetailActivity
import com.assel.footbalapp.activity.main.MainActivity
import com.assel.footbalapp.activity.main.MainViewModel
import com.assel.footbalapp.activity.main.schedule.ScheduleRecyclerAdapter
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.favourite_layout.view.*
import org.jetbrains.anko.support.v4.startActivity

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


//            recyclerView.layoutManager = LinearLayoutManager(context)
//            recyclerView.adapter = ScheduleRecyclerAdapter(listOf()) { event ->
//                startActivity<ScheduleDetailActivity>(AppConstant.EXTRA_EVENT to event)
//            }
//            val observer = Observer<List<Event>> {
//                if (it != null) {
//                    val adapter = recyclerView.adapter as ScheduleRecyclerAdapter
//                    adapter.events = it
//                    adapter.notifyDataSetChanged()
//                } else {
//                    println("null events")
//                }
//            }
//            val eventType = arguments?.getInt("event") ?: throw NullPointerException()
//            when (eventType) {
//                TYPE_LAST_EVENT -> viewModel.lastEvent.observe(this@FavouriteFragment, observer)
//                TYPE_NEXT_EVENT -> viewModel.nextEvent.observe(this@FavouriteFragment, observer)
//                TYPE_FAVOURITE -> viewModel.favouriteEvent.observe(this@FavouriteFragment, observer)
//            }
        }
    }
}
