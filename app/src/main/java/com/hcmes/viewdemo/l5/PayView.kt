package com.hcmes.viewdemo.l5

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import com.hcmes.viewdemo.R

class PayView: View{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init()
    }
    var mCurrentValue:Float=0f
    var paint=Paint()
    var dstPath=Path()
    var circlePath=Path()
    private var   XX:Float= 300f
    private var   YY:Float = 300f
    private  var mRadius:Float = 250f//圆心坐标
    var pathMeasure:PathMeasure?=null
    var mNext:Boolean=false
    fun init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null)

        paint.setColor(Color.BLACK)
        paint.strokeWidth=2f
        paint.style=Paint.Style.STROKE
        circlePath.addCircle(XX,YY,mRadius,Path.Direction.CW)//顺时针画图
        this.circlePath.moveTo(XX-mRadius/2,YY)
        this.circlePath.lineTo(XX,YY+mRadius/2)
        this.circlePath.lineTo(XX+mRadius/2f,YY-mRadius/3)
        this.pathMeasure= PathMeasure(this.circlePath,false)

        var valueAnimator=  ValueAnimator.ofFloat(0f,2f).setDuration(2000)
        valueAnimator.addUpdateListener {
            mCurrentValue=it.getAnimatedValue() as Float
            invalidate()
        }
        valueAnimator.repeatCount=ValueAnimator.INFINITE
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
         canvas.drawColor(Color.WHITE)
        if (this.mCurrentValue<1){
           var stop= pathMeasure!!.length*mCurrentValue
            pathMeasure!!.getSegment(0f,stop,dstPath,true)
        }else{
            if (!mNext){
                this.mNext=true
                this.pathMeasure!!.getSegment(0f,this.pathMeasure!!.length,dstPath,true)
                this.pathMeasure!!.nextContour()
            }
            var stop=pathMeasure!!.length*(mCurrentValue-1)
            pathMeasure!!.getSegment(0f,stop,dstPath,true)
        }
        canvas.drawPath(dstPath,paint);//把截取到的路径画出来

    }
}