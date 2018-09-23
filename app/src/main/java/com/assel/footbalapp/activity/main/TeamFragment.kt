package com.assel.footbalapp.activity.main

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.assel.footbalapp.R
import com.assel.footbalapp.model.League
import kotlinx.android.synthetic.main.team_layout.view.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.toast

class TeamFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as MainActivity
        viewModel = context.viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.team_layout, container, false).apply {
            viewModel.allLeague.observe(this@TeamFragment, Observer { leagues->
                if (leagues?.isNotEmpty() == true) {
                    spinner.adapter = ArrayAdapter<League>(context, android.R.layout.simple_dropdown_item_1line, leagues)
                            .apply {
                                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            }

                    spinner.onItemSelectedListener {
                        onItemSelected { adapterView, view, i, l ->
                            val selected = leagues.get(i)
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
                    isLoadedAll.observe(this@TeamFragment, Observer {
                        if (it == true) progressBar.visibility = View.GONE
                    })
                } else {
                    toast("No network").show()
                }
            })


        }
    }

}
