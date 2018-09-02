package com.assel.footbalapp.activity.main

import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import com.assel.footbalapp.R
import com.assel.footbalapp.activity.detail.DetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*


class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUI().setContentView(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)



        viewModel.eventsLiveData.observe(this, Observer {
            if (it != null) {
                val recyclerView = find<RecyclerView>(MainUI.Ids.recyclerView)
                recyclerView.adapter = MainAdapter(it) { event ->
                    startActivity<DetailActivity>("event" to event)
                }
                recyclerView.adapter?.notifyDataSetChanged()
            } else {
                println("null events")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuDate) {
            toast("Pick events date").show()
            val currentTime = viewModel.currentSelectedTime.value
            val calendar = Calendar.getInstance().apply {
                val formatDate = viewModel.dateFormat.parse(currentTime)
                time = formatDate
            }
            val newFragment = DatePickerFragment.newInstance(calendar)
            newFragment.show(supportFragmentManager, "datePicker")
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
        val strDate = "$year-${month+1}-$day"
        println("date: $strDate")
        viewModel.currentSelectedTime.postValue(strDate)
    }
}
