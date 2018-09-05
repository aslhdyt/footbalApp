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
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.recycler_layout.view.*
import org.jetbrains.anko.support.v4.startActivity

class TabFragment: Fragment() {
    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = (context as MainActivity).viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recycler_layout, container, false).also {

        }

        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.adapter = MainRecyclerAdapter(listOf()) { event ->
            startActivity<DetailActivity>(AppConstant.EXTRA_EVENT to event)
        }
        val observer = Observer<List<Event>> {
            if (it != null) {
                val adapter = view.recyclerView.adapter as MainRecyclerAdapter
                adapter.events = it
                adapter.notifyDataSetChanged()
            } else {
                println("null events")
            }
        }
        val isNextEvent = arguments?.getBoolean("event") ?: throw NullPointerException()
        if (isNextEvent) viewModel.nextEvent.observe(this@TabFragment, observer)
        else  viewModel.lastEvent.observe(this@TabFragment, observer)
        return view
    }

    companion object {
        fun newInstance(isNextEvent: Boolean): TabFragment {
            val args = Bundle()
            args.putBoolean("event", isNextEvent)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }
    }
    class Adapter(fragmentManager: FragmentManager, private val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int) = when (position) {
            0 -> TabFragment.newInstance(false)
            1 -> TabFragment.newInstance(true)
            else -> throw NullPointerException()
        }

        override fun getCount() = tabCount
    }
}