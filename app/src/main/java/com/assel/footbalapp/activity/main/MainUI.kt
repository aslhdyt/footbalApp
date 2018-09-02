package com.assel.footbalapp.activity.main

import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.relativeLayout

class MainUI : AnkoComponent<MainActivity>{
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            lparams { width = matchParent
            height = matchParent
            gravity = Gravity.CENTER}

            recyclerView {
                lparams(matchParent, matchParent)
                id = Ids.recyclerView
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    object Ids {
        const val recyclerView = 0
    }

}
