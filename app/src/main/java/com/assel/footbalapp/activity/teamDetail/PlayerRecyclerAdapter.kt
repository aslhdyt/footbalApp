package com.assel.footbalapp.activity.teamDetail

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.R


import com.assel.footbalapp.model.Player
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.backgroundColor

class PlayerRecyclerAdapter(
        var playerList: List<Player>,
        private val callback: (player: Player)->Unit)
    : RecyclerView.Adapter<PlayerRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_player, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = playerList[position]
        holder.itemView.apply {
            backgroundColor = if (position % 2 == 0)
                ContextCompat.getColor(context, R.color.grey_200)
            else
                ContextCompat.getColor(context, R.color.white)

            tvPlayerName.text = item.strPlayer
            tvPosition.text = item.strPosition
            Picasso.get().load(item.strCutout).into(ivPhoto)

            setOnClickListener{callback(item)}
        }
    }

    override fun getItemCount(): Int = playerList.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView)
}
