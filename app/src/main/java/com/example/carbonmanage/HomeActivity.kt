package com.example.carbonmanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private val _helper = DatabaseHelper(this@HomeActivity)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intentInput = Intent(applicationContext, InputActivity::class.java)
        btInput.setOnClickListener { startActivity(intentInput) }



    }
}


/* あとで試す
        val db = _helper.writableDatabase

        val sqlMonth = "SELECT SUM(amount) FROM carbonManage"

        val cursor = db.rawQuery(sqlMonth, null)
        var amountMonth:Int = 0
        if (cursor != null){
            while (cursor.moveToNext()){
                val idxCursor = cursor.getColumnIndex("amount")
                amountMonth = cursor.getInt(idxCursor)
            }

            monthlyAmount.setText(amountMonth)
        }
 */