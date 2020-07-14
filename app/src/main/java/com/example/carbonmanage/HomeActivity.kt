package com.example.carbonmanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_input.*
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.util.*

class HomeActivity : AppCompatActivity() {


    private val _helper = DatabaseHelper(this@HomeActivity)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val db = _helper.writableDatabase

        //日数計算に必要な変数
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month =  c.get(Calendar.MONTH)  //返された数字は、先月（今月-1）のものになっている
        val monthSpl = c.get(Calendar.MONTH) +1
        val daySql = c.get(Calendar.DAY_OF_MONTH)
        val monthSqlFom = "%1$02d".format(monthSpl)
        val lastMonSqlFom = "%1$02d".format(month)
        val thisMonth = "$year/$monthSqlFom%"
        val lastMonth = "$year/$lastMonSqlFom%"

        //今月の糖質量を算出
        val sqlMonth = "select round(sum(carbon),1) from carbonManage where date like '${thisMonth}'"
        val cursor = db.rawQuery(sqlMonth, null)
        var amountMonth = ""
        if (cursor.moveToNext()) {
//                val idxCursor = cursor.getColumnIndex("amount") 恐らくsumだと列名がcursorに入らないためamountが認識されず、getColumnIndexは-1になってしまう。
                amountMonth = cursor.getDouble(0).toString()
            monthlyAmount.setText(amountMonth + " " + "グラム")
        }
        val intentInput = Intent(applicationContext, InputActivity::class.java)
        btInput.setOnClickListener { startActivity(intentInput) }

        //一日当たりの糖質摂取量計算
        c.set(year, month, 1)
        val bMonthTimeMillis = c.timeInMillis
        val currentTimeMillis = System.currentTimeMillis()
        val diff = (currentTimeMillis - bMonthTimeMillis)/(1000 * 60 * 60 * 24)
        val DailyCarbon = (amountMonth.toDouble() / diff) as Double
        val bdDailyCarbon = BigDecimal(DailyCarbon)
        val bdDailyCarbon_1 = bdDailyCarbon.setScale(1,BigDecimal.ROUND_HALF_UP)
        val txDailyCarbon = bdDailyCarbon_1.toString()
        dailyCarbon.setText(txDailyCarbon)

        //先月の日数計算
        c.set(year, month, 1)
        val bigLastMonTimeMillis = c.timeInMillis
        val endOfMonthDay = c.getMaximum(Calendar.DATE)
        c.set(year, month, endOfMonthDay)
        val endLastMonTimeMillis = c.timeInMillis
        val deff_last = (endLastMonTimeMillis - bigLastMonTimeMillis)/(1000 * 60 * 60 * 24)

        //先月の糖質量の合計を算出
        val sqlLastMonth = "select round(sum(carbon),1) from CarbonManage where date like '${lastMonth}' "
        val cursorLastMon = db.rawQuery(sqlLastMonth, null)
        var amountLastMon = ""
        if (cursorLastMon.moveToNext()){
            amountLastMon = cursorLastMon.getDouble(0).toString()
        }
        val lasDailyCarbon = (amountLastMon.toDouble()/deff_last)
        val bdLasDailyCarbon = BigDecimal(lasDailyCarbon)
        val bdLastDailyCabon_1 = bdLasDailyCarbon.setScale(1,BigDecimal.ROUND_HALF_UP)
        val txLastDailyCarbon = bdLastDailyCabon_1.toString()
        lastMonthDaily.setText(txLastDailyCarbon)

        //chartActivity に移動する
        val intentChart = Intent(applicationContext,ChartActivity::class.java)
        btDisplay.setOnClickListener {
            startActivity(intentChart)
        }

    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

}


