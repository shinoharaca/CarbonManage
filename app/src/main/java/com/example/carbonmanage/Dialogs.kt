package com.example.carbonmanage

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

//食事登録の日付をダイアログで選択
class DatePickerFragment: DatePickerDialog.OnDateSetListener,DialogFragment(){

    interface OnDateSelectedListener {
        fun Selected(year:Int, month: Int, date: Int)
    }

    private var listener: OnDateSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context) {
            is OnDateSelectedListener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = java.util.Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(),this, year, month, date)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, date: Int) {
        listener?.Selected(year,month,date)
    }

}

class TimePickerFragment: DialogFragment(),TimePickerDialog.OnTimeSetListener{

    interface OnTimeSelectedListener{
        fun Selected(hourOfDay: Int, minute: Int)
    }

    private var listener : OnTimeSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context) {
            is TimePickerFragment.OnTimeSelectedListener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = java.util.Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context, this, hour, minute, true)
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        listener?.Selected(p1, p2)
    }

}