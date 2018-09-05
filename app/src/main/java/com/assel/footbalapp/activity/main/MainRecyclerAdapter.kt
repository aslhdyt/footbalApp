package com.assel.footbalapp.activity.main

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.R
import com.assel.footbalapp.model.Event
import kotlinx.android.synthetic.main.item_event.view.*
import org.jetbrains.anko.backgroundColor

class MainRecyclerAdapter(var events: List<Event>, val onItemClick: (Event)-> Unit): RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_event, p0, false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(p1)
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(pos: Int) {
            val event = events[pos]
            if (pos % 2 == 0)
                itemView.backgroundColor = ContextCompat.getColor(itemView.context, R.color.grey_200)
            else
                itemView.backgroundColor = ContextCompat.getColor(itemView.context, R.color.white)
            itemView.setOnClickListener { onItemClick(event) }
            itemView.apply {
                tvTitle.text = event.strEvent
                tvTeamHome.text = event.strHomeTeam
                tvTeamAway.text = event.strAwayTeam
                tvScore.text = String.format("%s vs %s", event.intHomeScore ?: "", event.intAwayScore ?: "")
            }

        }
    }
}