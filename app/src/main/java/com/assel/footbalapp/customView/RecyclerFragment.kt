package com.assel.footbalapp.customView

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.AppConstant.TYPE_DB_EVENT
import com.assel.footbalapp.AppConstant.TYPE_DB_TEAM
import com.assel.footbalapp.AppConstant.TYPE_LAST_EVENT
import com.assel.footbalapp.AppConstant.TYPE_NEXT_EVENT
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.main.MainActivity
import com.assel.footbalapp.activity.main.MainViewModel
import com.assel.footbalapp.activity.main.schedule.ScheduleRecyclerAdapter
import com.assel.footbalapp.activity.main.team.TeamRecylerAdapter
import com.assel.footbalapp.activity.scheduleDetail.ScheduleDetailActivity
import com.assel.footbalapp.activity.teamDetail.TeamDetailActivity
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.recycler_layout.view.*
import org.jetbrains.anko.support.v4.startActivity

class RecyclerFragment: Fragment() {


    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as MainActivity
        viewModel = context.viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recycler_layout, container, false).apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            val type = arguments?.getInt(AppConstant.EXTRA_RECYCLER_TYPE, -1)
            when(type) {
                TYPE_LAST_EVENT, TYPE_NEXT_EVENT, TYPE_DB_EVENT -> {
                    val observer = Observer<List<Event>> {
                        if (it != null) {
                            val adapter = recyclerView.adapter as ScheduleRecyclerAdapter
                            adapter.events = it
                            adapter.notifyDataSetChanged()
                        }
                    }
                    recyclerView.adapter = ScheduleRecyclerAdapter(listOf()) { event ->
                        startActivity<ScheduleDetailActivity>(AppConstant.EXTRA_EVENT to event)
                    }
                    when (type) {
                        TYPE_LAST_EVENT -> viewModel.lastEvent.observe(this@RecyclerFragment, observer)
                        TYPE_NEXT_EVENT -> viewModel.nextEvent.observe(this@RecyclerFragment, observer)
                        TYPE_DB_EVENT -> viewModel.favouriteEvent.observe(this@RecyclerFragment, observer)
                    }

                }
                TYPE_DB_TEAM -> {
                    recyclerView.adapter = TeamRecylerAdapter(listOf()) { team ->
                        startActivity<TeamDetailActivity>(AppConstant.EXTRA_TEAM to team)
                    }
                    viewModel.favouriteTeam.observe(this@RecyclerFragment, Observer {
                        if (it != null) {
                            val adapter = recyclerView.adapter as TeamRecylerAdapter
                            adapter.teams = it
                            adapter.notifyDataSetChanged()
                        }
                    })
                }
                else -> throw IllegalArgumentException("unregistered type, code $type")
            }

        }
    }

    companion object {
        fun newInstance(type: Int): RecyclerFragment {
            val args = Bundle()
            args.putInt(AppConstant.EXTRA_RECYCLER_TYPE, type)
            val fragment = RecyclerFragment()
            fragment.arguments = args
            return fragment
        }
    }


}
