package com.assel.footbalapp.activity.search

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.assel.footbalapp.IntentVmFactory
import com.assel.footbalapp.R
import org.jetbrains.anko.appcompat.v7.coroutines.onQueryTextListener


class SearchActivity : AppCompatActivity() {
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewModel = ViewModelProviders.of(this, IntentVmFactory(application, intent)).get(SearchViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView?
        searchView?.onQueryTextListener {
            onQueryTextSubmit {
                viewModel.doSearch(it)
                true
            }
        }
        return true
    }

}
