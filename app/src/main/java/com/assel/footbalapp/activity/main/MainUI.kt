package com.assel.footbalapp.activity.main

import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class MainUI : AnkoComponent<MainActivity>{
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout{
            lparams(width = matchParent, height = matchParent)
            tabLayout {
                elevation = 6f
                id = Ids.tabLayout
            }.lparams(width = matchParent, height = wrapContent)
            viewPager {
                id = Ids.viewPager
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    object Ids {
        const val viewPager = 1
        const val tabLayout = 2
    }
}
