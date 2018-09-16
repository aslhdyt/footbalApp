package com.assel.footbalapp.activity.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.detail.DetailActivity
import com.assel.footbalapp.idlingResource
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.recycler_layout.view.*
import org.jetbrains.anko.support.v4.startActivity

class TabFragment: Fragment() {


    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as MainActivity
        viewModel = context.viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recycler_layout, container, false).apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = MainRecyclerAdapter(listOf()) { event ->
                startActivity<DetailActivity>(AppConstant.EXTRA_EVENT to event)
            }
            val observer = Observer<List<Event>> {
                if (it != null) {
                    val adapter = recyclerView.adapter as MainRecyclerAdapter
                    adapter.events = it
                    adapter.notifyDataSetChanged()
                } else {
                    println("null events")
                }
                activity?.application?.idlingResource?.decrement()
            }
            val eventType = arguments?.getInt("event") ?: throw NullPointerException()
            when (eventType) {
                TYPE_LAST_EVENT -> viewModel.lastEvent.observe(this@TabFragment, observer)
                TYPE_NEXT_EVENT -> viewModel.nextEvent.observe(this@TabFragment, observer)
                TYPE_FAVOURITE -> viewModel.favouriteEvent.observe(this@TabFragment, observer)
            }
        }
    }

    companion object {
        fun newInstance(eventType: Int): TabFragment {
            val args = Bundle()
            args.putInt("event", eventType)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }

        const val TYPE_LAST_EVENT = 0
        const val TYPE_NEXT_EVENT = 1
        const val TYPE_FAVOURITE = 2
    }
    class Adapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int) = when (position) {
            TYPE_LAST_EVENT -> TabFragment.newInstance(TYPE_LAST_EVENT)
            TYPE_NEXT_EVENT -> TabFragment.newInstance(TYPE_NEXT_EVENT)
            TYPE_FAVOURITE -> TabFragment.newInstance(TYPE_FAVOURITE)
            else -> throw NullPointerException()
        }

        override fun getCount() = tabCount
    }

}
