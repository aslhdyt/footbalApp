package com.assel.footbalapp.activity.main.schedule

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.main.MainActivity
import com.assel.footbalapp.activity.main.MainViewModel
import com.assel.footbalapp.model.League
import kotlinx.android.synthetic.main.tab_layout.view.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.toast

class ScheduleFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as MainActivity
        viewModel = context.viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_layout, container, false).apply {
            viewModel.allLeague.observe(this@ScheduleFragment, Observer {leagues ->
                if (leagues?.isNotEmpty() == true) {
                    spinner.adapter = ArrayAdapter<League>(context, android.R.layout.simple_dropdown_item_1line, leagues)
                            .apply {
                                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            }
                    spinner.onItemSelectedListener {
                        onItemSelected { adapterView, view, i, l ->
                            val selected = leagues[i]
                            viewModel.currentSelectedLegue.postValue(selected.idLeague?.toIntOrNull() ?: 0)
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    val isLoadedAll = MediatorLiveData<Boolean>()
                    isLoadedAll.addSource(viewModel.lastEvent) { lastEvent ->
                        val nextEvent = viewModel.nextEvent.value
                        isLoadedAll.value = nextEvent != null && lastEvent != null
                    }
                    isLoadedAll.addSource(viewModel.nextEvent) { nextEvent ->
                        val lastEvent = viewModel.lastEvent.value
                        isLoadedAll.value = nextEvent != null && lastEvent != null
                    }
                    isLoadedAll.observe(this@ScheduleFragment, Observer {
                        if (it == true) progressBar.visibility = View.GONE
                    })
                } else {
                    toast("No network").show()
                }
            })

            viewPager.adapter = SchedulePagerAdapter(childFragmentManager, tabLayout.tabCount)

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


}
