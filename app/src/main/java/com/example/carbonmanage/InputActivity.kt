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

    private val _helper = DatabaseHelper(this@InputActivity)

    private var _mealId = -1

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

        btCarbonSave.setOnClickListener {
            val date = textDate.toString()
            val time = textTime.toString()
            val mealCase = spinner.toString()
            val meal = editMeal.toString()
            val amount = amount as Double
            val unit = textUnit.toString()
            val carbon = editCarbonAmount as Double

            val db = _helper.writableDatabase

            val sqlInsert =
                "INSERT INTO carbonManage (date, time, mealCase, meal, amount, unit, carbon) VALUES(?,?,?,?,?,?,?) "

            var stmt = db.compileStatement(sqlInsert)

            stmt.bindString(1, date)
            stmt.bindString(2, time)
            stmt.bindString(3, mealCase )
            stmt.bindString(4, meal)
            stmt.bindDouble(5, amount)
            stmt.bindString(6, unit)
            stmt.bindDouble(7, carbon)

            stmt.executeInsert()
        }

    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

}