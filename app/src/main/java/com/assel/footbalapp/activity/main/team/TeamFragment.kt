package com.assel.footbalapp.activity.main.team

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.main.MainActivity
import com.assel.footbalapp.activity.main.MainViewModel
import com.assel.footbalapp.activity.teamDetail.TeamDetailActivity
import com.assel.footbalapp.model.League
import kotlinx.android.synthetic.main.team_layout.view.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.startActivity
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
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = TeamRecylerAdapter(listOf()) { team ->
                startActivity<TeamDetailActivity>(AppConstant.EXTRA_TEAM to team)
            }
            viewModel.allLeague.observe(this@TeamFragment, Observer { leagues->
                if (leagues?.isNotEmpty() == true) {
                    spinner.adapter = ArrayAdapter<League>(context, android.R.layout.simple_dropdown_item_1line, leagues)
                            .apply {
                                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            }

                    spinner.onItemSelectedListener {
                        onItemSelected { adapterView, view, i, l ->
                            progressBar.visibility = View.VISIBLE
                            val selected = leagues[i]
                            viewModel.currentSelectedLegue.postValue(selected.idLeague?.toIntOrNull() ?: 0)
                        }
                    }
                    viewModel.team.observe(this@TeamFragment, Observer {
                        if (it != null) {
                            progressBar.visibility = View.GONE
                            val adapter = recyclerView.adapter as TeamRecylerAdapter
                            adapter.teams = it
                            adapter.notifyDataSetChanged()
                        } else {
                            //TODO no data
                        }
                    })
                } else {
                    toast("No network").show()
                }
            })



        }
    }
}
