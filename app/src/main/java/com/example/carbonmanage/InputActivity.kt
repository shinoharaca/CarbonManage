package com.example.carbonmanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.format.DateFormat
import android.widget.DatePicker
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_input.*
import java.util.*

class InputActivity : AppCompatActivity(),
    DatePickerFragment.OnDateSelectedListener,
    TimePickerFragment.OnTimeSelectedListener{


    override fun Selected(year: Int, month: Int, date: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, date)
        textDate.text = DateFormat.format("yyyy/MM/dd", c)
    }

    override fun Selected(hourOfDay: Int, minute: Int) {
        val c = Calendar.getInstance()
        c.set(hourOfDay,minute)
        textTime.text = "%1$02d:%2$02d".format(hourOfDay, minute)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        textDate.setOnClickListener {
            val dialog = DatePickerFragment()
            dialog.show(supportFragmentManager,"date")
        }

        textTime.setOnClickListener {
            val dialog = TimePickerFragment()
            dialog.show(supportFragmentManager,"time")
        }

    }


}