package com.assel.footbalapp.activity.player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.assel.footbalapp.AppConstant
import com.assel.footbalapp.R
import com.assel.footbalapp.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        val player = intent.getParcelableExtra<Player>(AppConstant.EXTRA_PLAYER)

        Picasso.get().load(player.strThumb).into(ivPict)

        tvPosition.text = player.strPosition
        tvWeight.text = player.strWeight
        tvHeight.text = player.strHeight
        tvDesc.text = player.strDescriptionEN
    }
}
