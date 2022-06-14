package com.hcmes.viewdemo.l6

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
class PaintView: View{
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

    fun init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null)


    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var baseLineX=0f
        var baseLineY=200f
        //画基线
        var paint=Paint()
        paint.setColor(Color.RED)
        canvas.drawLine(baseLineX,baseLineY,3000f,baseLineY,paint)
        //写文字
        paint.setColor(Color.GREEN)
        paint.textSize=120f
     //   paint.textAlign=Paint.Align.CENTER
        canvas.drawText("harvic's blog",baseLineX,baseLineY,paint)
       // canvas.drawText("A",baseLineX,baseLineY+10,paint)
       var fontMetrics= paint.getFontMetrics()
      var ascent=  fontMetrics.ascent+baseLineY
      var descent=  fontMetrics.descent+baseLineY
      var top=  fontMetrics.top+baseLineY
       var bottom= fontMetrics.bottom+baseLineY

       paint.setColor(Color.BLUE)
       canvas.drawLine(baseLineX,top,3000f,top,paint)

        paint.setColor(Color.GREEN)
        canvas.drawLine(baseLineX,ascent,3000f,ascent,paint)

        paint.setColor(Color.GREEN)
        canvas.drawLine(baseLineX,descent,3000f,descent,paint)

        paint.setColor(Color.RED)
        canvas.drawLine(baseLineX,bottom,3000f,bottom,paint)

        //得到文字高度

        var height=bottom-top;
        var widht= paint.measureText("")
    }
}