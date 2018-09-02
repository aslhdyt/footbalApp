package com.assel.footbalapp.activity.main

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = activity as DatePickerDialog.OnDateSetListener
        val c = Calendar.getInstance()
        val year = arguments?.getInt("year") ?: c.get(Calendar.YEAR)
        val month = arguments?.getInt("month") ?: c.get(Calendar.MONTH)
        val day = arguments?.getInt("day") ?: c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, listener, year, month, day)
    }

    companion object {

        fun newInstance(calendar: Calendar): DatePickerFragment {
            val fragment = DatePickerFragment()
            val args = Bundle()
            args.putInt("year", calendar.get(Calendar.YEAR))
            args.putInt("month", calendar.get(Calendar.MONTH))
            args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH))

            fragment.arguments = args
            return fragment
        }
    }
}