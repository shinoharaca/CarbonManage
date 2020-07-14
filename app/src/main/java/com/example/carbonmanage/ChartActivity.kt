package com.example.carbonmanage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.LinearLayout
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.activity_chart.view.*
import com.example.carbonmanage.ChartCreate as ChartCreate

class ChartActivity : AppCompatActivity() ,Animation.AnimationListener{

    private lateinit var chart : BarChart
    lateinit var chartCreate:ChartCreate
    lateinit var animationForChartView: AnimationForChartView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        chart = findViewById(R.id.barChart)

        //表示データ取得
        val data = BarData(getBarData())
        chart.data = data

        //Y軸
        val left = chart.axisLeft
        left.axisMinimum = 0F
        left.axisMaximum = 10000f
        left.labelCount = 5
        left.setDrawTopYLabelEntry(true)

        //Ｙ軸　右
        val right = chart.axisRight
        right.setDrawLabels(false)
        right.setDrawZeroLine(true)
        right.setDrawGridLines(false)
        right.setDrawTopYLabelEntry(false)

        //X軸
        val xAxis = chart.xAxis
        var  labels = listOf("","3月","4月","5月","6月","7月")
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        val bottomAxis = chart.xAxis
        bottomAxis.position = XAxis.XAxisPosition.BOTTOM
        bottomAxis.setDrawLabels(true)
        bottomAxis.setDrawGridLines(false)
        bottomAxis.setDrawAxisLine(true)
        bottomAxis.textSize =10f
        bottomAxis.mLabelWidth = 100
        bottomAxis.setLabelCount(5)

        //棒グラフ上の表示
        chart.setDrawValueAboveBar(true)
        chart.description.isEnabled = false
        chart.isClickable = false

        //棒グラフ凡例
        chart.legend.isEnabled = false

        chart.setScaleEnabled(false)
        //棒グラフアニメーション
        chart.animateY(1200, Easing.Linear)


// 円グラフ
        drawChart()
    }

//円グラフのアニメーションのメソッド
    private fun drawChart(){
        chartCreate = ChartCreate(applicationContext)
        val rate = 20
        val animationForChartView = AnimationForChartView(chartCreate)
        animationForChartView.rate = rate
        animationForChartView.duration =  2000
//        val pieChart = findViewById<View>(R.id.pieChart)
//        pieChart.startAnimation(animationForChartView)
        chartCreate.startAnimation(animationForChartView)
    }

    //棒グラフのデータ
    fun getBarData(): ArrayList<IBarDataSet> {
//        var data = mutableListOf<Entry>(2000, 3000, 4000)
        val entries = mutableListOf<BarEntry>()
        entries.add(BarEntry(1f ,1000f,null,null))
        entries.add(BarEntry(2f,3000f,null,null))
        entries.add(BarEntry(3f,6000f,null,null))
        entries.add(BarEntry(4f,8000f,null,null))
        entries.add(BarEntry(5f,9000f,null,null))

        val bars = ArrayList<IBarDataSet>()
        val dataset = BarDataSet(entries, "bar")
        bars.add(dataset)

        return bars
    }


    override fun onAnimationRepeat(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {

    }

    override fun onAnimationStart(p0: Animation?) {

    }

}

