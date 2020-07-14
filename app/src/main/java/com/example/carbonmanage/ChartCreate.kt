package com.example.carbonmanage

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_chart.view.*

class ChartCreate: View {

    var rate:Float = 0f
    var isNotClockWise:Boolean = false

    constructor(context: Context):super(context)
    constructor(context: Context,attrs :AttributeSet):super(context,null)
    constructor(context: Context,attrs: AttributeSet,defStyleAttr:Int):super(context,null,0)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBaseChart(canvas)
        drawValueChart(canvas)
    }


    private fun drawBaseChart(canvas: Canvas?){
        val paint = Paint()
        paint.color = Color.rgb(255,212,121)
        val strokeWidth = (width / 8).toFloat()
        paint.strokeWidth = strokeWidth
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        val rect = RectF(strokeWidth/2*4, strokeWidth/2*4,
            width.toFloat()-strokeWidth*2 , height.toFloat()-strokeWidth/2)
        canvas!!.drawArc(rect, 0f, 360f, false, paint)
    }



    private fun drawValueChart(canvas: Canvas?){
        val paint = Paint()
        paint.color = Color.rgb(255,138,216)
        val strokeWidth = (width/8).toFloat()
        paint.strokeWidth = strokeWidth
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND

        val rect = RectF(strokeWidth/2*4, strokeWidth/2*4,
            width.toFloat()-strokeWidth*2 , height.toFloat()-strokeWidth/2)
        var angle = rate / 100 * 360
        if (isNotClockWise){
            angle *= -1
        }
        canvas!!.drawArc(rect, -90f, angle, false, paint)
    }

}


class AnimationForChartView:Animation {
    lateinit var chartCreate: ChartCreate

    var rate: Int = 0

    constructor(chartCreate: ChartCreate)  {
        this.chartCreate = chartCreate
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime,  t)
        val thisRate = rate*interpolatedTime
        chartCreate.rate = thisRate
        chartCreate.requestLayout()
    }
}










