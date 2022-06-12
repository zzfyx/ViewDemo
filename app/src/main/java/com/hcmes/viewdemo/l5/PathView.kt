package com.hcmes.viewdemo.l5

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator

class PathView: View{
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
    var currentFloat:Float=0f
    fun init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null)
        var valueAnimator=  ValueAnimator.ofFloat(0f,1f).setDuration(2000)
        valueAnimator.addUpdateListener {
            currentFloat=it.getAnimatedValue() as Float
            invalidate()
        }
        valueAnimator.repeatCount=ValueAnimator.INFINITE
       // valueAnimator.interpolator=BounceInterpolator()
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint= Paint()
        paint.setColor(Color.BLACK)
        paint.strokeWidth=2f
        paint.style=Paint.Style.STROKE
        var path=Path()
        canvas.translate(200f,200f)
/*


        path.moveTo(0f,100f)
        path.lineTo(0f,100f)
        path.lineTo(100f,100f)
        path.lineTo(100f,0f)
        var measure1=PathMeasure(path,false)
        var measure2=PathMeasure(path,true)


        Log.e("qijian","forceClose=false-->"+measure1.length)
        Log.e("qijian","forceClose=true-->"+measure2.length)
        canvas.drawPath(path,paint)
*/


     /*
        path.addRect(-50f,-50f,50f,50f,Path.Direction.CCW)
        path.addRect(-100f,-100f,100f,100f,Path.Direction.CCW)
        path.addRect(-120f,-120f,120f,120f,Path.Direction.CW)
        canvas.drawPath(path,paint)
        var measure=PathMeasure(path,false)

        do {
         var len=   measure.length;
            Log.i("qijian","len="+len)
        }while (measure.nextContour())*/


        path.addCircle(100f,100f,50f,Path.Direction.CW)
        canvas.drawPath(path,paint)
        var dst=Path()
        dst.lineTo(10f,100f)
        var measure=PathMeasure(path,false)


        var stop=currentFloat*measure.length
        var start=stop-(0.5f-Math.abs(currentFloat-0.5f))*measure.length
        Log.i("qijian","len="+start)
        //最后一个函数，如果startWithmoveTo为true，路径起始点移动到新添加路径的起始点，否则使用之前的路径直接连过来，不会调用moveto
      //  dst.reset()
        measure.getSegment(start,stop,dst,true)
        paint.setColor(Color.RED)
        canvas.drawPath(dst,paint)


    }
}