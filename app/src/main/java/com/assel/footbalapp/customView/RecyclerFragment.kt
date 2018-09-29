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
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.main.MainActivity
import com.assel.footbalapp.activity.main.MainViewModel
import com.assel.footbalapp.activity.main.schedule.ScheduleRecyclerAdapter
import com.assel.footbalapp.activity.scheduleDetail.ScheduleDetailActivity
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.recycler_layout.view.*
import org.jetbrains.anko.support.v4.startActivity
import java.lang.IllegalArgumentException

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
            recyclerView.adapter = ScheduleRecyclerAdapter(listOf()) { event ->
                startActivity<ScheduleDetailActivity>(AppConstant.EXTRA_EVENT to event)
            }
            val type = arguments?.getInt(AppConstant.EXTRA_RECYCLER_TYPE, -1)
            when(type) {
                TYPE_LAST_EVENT, TYPE_NEXT_EVENT ->{
                    val observer = Observer<List<Event>> {
                        if (it != null) {
                            val adapter = recyclerView.adapter as ScheduleRecyclerAdapter
                            adapter.events = it
                            adapter.notifyDataSetChanged()
                        } else {
                            println("null events")
                        }
                    }
                    when (type) {
                        TYPE_LAST_EVENT -> viewModel.lastEvent.observe(this@RecyclerFragment, observer)
                        TYPE_NEXT_EVENT -> viewModel.nextEvent.observe(this@RecyclerFragment, observer)
                    }

                }
                TYPE_DB_EVENT ->{

                }
                TYPE_DB_TEAM ->{}
                else -> throw IllegalArgumentException()
            }

        }
    }

    companion object {
        fun newInstance(eventType: Int): RecyclerFragment {
            val args = Bundle()
            args.putInt(AppConstant.EXTRA_RECYCLER_TYPE, eventType)
            val fragment = RecyclerFragment()
            fragment.arguments = args
            return fragment
        }

        const val TYPE_LAST_EVENT = 0
        const val TYPE_NEXT_EVENT = 1
        const val TYPE_DB_EVENT = 2
        const val TYPE_DB_TEAM = 3
    }


}
