package com.hcmes.viewdemo.l6

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.view.marginTop

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
   /*     var baseLineX=0f
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
        //得到文字的宽度
        var widht= paint.measureText("")*/

        draw1(canvas)

    }
    fun draw1(canvas: Canvas){
        //根据矩形左上角绘制文字过程
        var text="harvic\\'s blog"
        var top=0f
        var baseLineX=0f
        var paint=Paint()
        paint.textSize=120f
        paint.textAlign=Paint.Align.LEFT
        paint.color=Color.YELLOW
        paint.setPathEffect(CornerPathEffect(10f))
        paint.setStrokeJoin(Paint.Join.MITER)
        paint.isUnderlineText=true//下划线
        paint.isStrikeThruText=true//加粗
        paint.textSkewX=0.1f//水平倾斜
        paint.isSubpixelText=true//亚像素
        //画top线
        canvas.drawLine(baseLineX,top,3000f,top,paint)
        var fm=paint.getFontMetricsInt()
        var baseLineY=top-fm.top
        //画基线
        paint.color=Color.RED
        canvas.drawLine(baseLineX,baseLineY,3000f,baseLineY,paint)
        //写文字
        paint.setColor(Color.GREEN)
        canvas.drawText(text,baseLineX,baseLineY,paint)
        var rect=Rect()
        paint.getTextBounds(text,0,text.length,rect)
        canvas.drawRect(rect,paint)
        //baseline= center + (FontMetrics.bottom - FontMetrics.top)/2 -FontMetrics.bottom
    }
    fun getBaseline(p: Paint): Float {
        val fontMetrics = p.fontMetricsInt
        return (fontMetrics.descent - fontMetrics.ascent) / 2f - fontMetrics.descent
    }
}