package com.assel.footbalapp.activity.main

import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainUI : AnkoComponent<MainActivity>{
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        linearLayout {
            lparams(matchParent, matchParent)
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
