package com.example.carbonmanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.format.DateFormat
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_imput.*
import kotlinx.android.synthetic.main.activity_input.*
import kotlinx.android.synthetic.main.activity_input.editMeal
import kotlinx.android.synthetic.main.activity_input.spinner
import org.w3c.dom.Text
import java.time.temporal.ChronoUnit
import java.util.*
import android.widget.AdapterView.OnItemSelectedListener as OnItemSelectedListener1

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

        //スピナーを選択したときの処理
        var mealCase = ""
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                val item = p0.getItemAtPosition(p2) as String
                mealCase = item
            }
        }


        btCarbonSave.setOnClickListener {
            val textD = findViewById<TextView>(R.id.textDate) //日付
            val textT = findViewById<TextView>(R.id.textTime) //時間
            val editM = findViewById<EditText>(R.id.editMeal) //食べたもの、飲んだもの
            val editA = findViewById<EditText>(R.id.editAmount) //（食べた）量
            val editU = findViewById<EditText>(R.id.editUnit) //単位
            val editC = findViewById<EditText>(R.id.editCarbon) //（食べた）糖質量
            val carbonDouble = editC.text.toString().toDouble() //テキストを小数に変換

            val date = textD.text.toString()
            val time = textT.text.toString()
            val meal = editM.text.toString()
            val amount = editA.text.toString().toLong()
            val unit = editU.text.toString()
            val carbon = carbonDouble

            val db = _helper.writableDatabase

            val sqlInsert =
                "INSERT INTO carbonManage (date, time, mealCase, meal, amount, unit, carbon) VALUES(?,?,?,?,?,?,?) "

            var stmt = db.compileStatement(sqlInsert)

            stmt.bindString(1, date)
            stmt.bindString(2, time)
            stmt.bindString(3, mealCase )
            stmt.bindString(4, meal)
            stmt.bindLong(5, amount)
            stmt.bindString(6, unit)
            stmt.bindDouble(7, carbon)

            stmt.executeInsert()

            textDate.setText("")
            textTime.setText("")
            spinner.setSelection(0)
            editMeal.setText("")
            editA.setText("")
            editU.setText("")
            editC.setText("")

            Toast.makeText(this, "登録しました",Toast.LENGTH_SHORT ).show()

        }


    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

}


