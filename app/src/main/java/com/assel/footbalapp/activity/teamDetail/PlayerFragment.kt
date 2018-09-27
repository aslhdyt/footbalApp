package com.assel.footbalapp.activity.teamDetail

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.player.PlayerDetailActivity
import kotlinx.android.synthetic.main.team_layout.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PlayerFragment.OnListFragmentInteractionListener] interface.
 */
class PlayerFragment : Fragment() {

    lateinit var viewModel: TeamDetailViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context as TeamDetailActivity
        viewModel = context.viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = PlayerRecyclerAdapter(listOf()) {
                    startActivity<PlayerDetailActivity>(AppConstant.EXTRA_PLAYER to it)
                }
            }
        }

        viewModel.playersData.observe(this, Observer {
            if (it != null) {
                val adapter = recyclerView.adapter as PlayerRecyclerAdapter
                adapter.playerList = it
                adapter.notifyDataSetChanged()
            } else {
                toast("Failed to get players").show()
            }
        })


        return view
    }
}
