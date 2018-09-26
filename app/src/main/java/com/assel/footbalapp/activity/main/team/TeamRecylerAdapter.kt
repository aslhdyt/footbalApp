package com.assel.footbalapp.activity.main.team

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.R
import com.assel.footbalapp.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.backgroundColor


class TeamRecylerAdapter(var teams: List<Team>, val onItemClick: (Team)-> Unit): RecyclerView.Adapter<TeamRecylerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_team, p0, false))
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(p1)
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(pos: Int) {
            val team = teams[pos]
            if (pos % 2 == 0)
                itemView.backgroundColor = ContextCompat.getColor(itemView.context, R.color.grey_200)
            else
                itemView.backgroundColor = ContextCompat.getColor(itemView.context, R.color.white)
            itemView.setOnClickListener { onItemClick(team) }

            itemView.apply {
                tvTeamName.text = team.strTeam
                Picasso.get().load(team.strTeamBadge).into(ivTeamBadge)
            }

        }
    }
}