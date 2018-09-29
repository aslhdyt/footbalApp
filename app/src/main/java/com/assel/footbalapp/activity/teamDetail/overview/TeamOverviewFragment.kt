package com.assel.footbalapp.activity.teamDetail.overview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.teamDetail.TeamDetailActivity
import com.assel.footbalapp.activity.teamDetail.TeamDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.overview_layout.view.*

class TeamOverviewFragment : Fragment() {
    lateinit var viewModel: TeamDetailViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as TeamDetailActivity
        viewModel = context.viewModel
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val model = viewModel.team
        return inflater.inflate(R.layout.overview_layout, container, false).apply {
            Picasso.get().load(model.strTeamBadge).into(ivTeamBadge)
            tvTeamName.text = model.strTeam
            tvYear.text = model.intFormedYear
            tvStadium.text = model.strStadium
            tvDesc.text = model.strDescriptionEN

        }
    }

}
