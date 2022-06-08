package com.hcmes.cosumeviewdemo.l1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BasisView:View{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context, attrs, defStyleAttr, defStyleRes)

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
       //设置画笔基本属性
        var paint=Paint()
        paint.setColor(Color.RED)//设置画笔颜色
      //  paint.setStyle(Paint.Style.STROKE)//设置画笔样式 描边
       // paint.setStyle(Paint.Style.FILL_AND_STROKE)//设置画笔样式  填充和描边
        paint.setStyle(Paint.Style.FILL)//设置画笔样式    填充内部
        paint.strokeWidth=50f//设置画笔宽度
        canvas!!.drawCircle(190f,200f,150f,paint)
        paint.setColor(0x7EFFFF00);
        canvas.drawCircle(190f,200f,100f,paint)
        //背景颜色设置
        canvas.drawColor(Color.BLUE)
        canvas.drawARGB(255,255,255,255)
        canvas.drawRGB(255,255,255)
        //画直线
        canvas.drawLine(190f,200f,150f,250f,paint)
        //画点
        canvas.drawPoint(100f,120f,paint)
        //矩形工具类Rect RectF
        paint.setColor(Color.CYAN)
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=1f
        var rectf=RectF(150f,150f,500f,500f)
        canvas.drawRect(rectf,paint)
        //路径 path  直线 弧线
        var path= Path()
        path.moveTo(100f,100f)
        path.lineTo(200f,200f)
        path.lineTo(200f,100f)
        path.lineTo(100f,100f)
        path.close()
        canvas.drawPath(path,paint)
        path.reset()

        var rectf2=RectF(150f,150f,500f,500f)
        path.arcTo(rectf2,0f,90f,true)//最后一个参数，强制把弧的起始点作为绘制的起始点
        path.close()

        paint.setColor(Color.GREEN)
        paint.strokeWidth=4f
        canvas.drawPath(path,paint);
        //区域Region canvas没有对应的画的方法需要自己画出来 ,Region不使用来绘画的，最重要是区域相交操作
        var region=Region(Rect(111,111,222,222))
        drawRegion(canvas,region,paint)

        region(canvas,paint)
        regionOp(canvas,paint)
        regionOP2(canvas,paint)
    }
    fun region(canvas: Canvas,paint: Paint){
        //构造一条椭圆路径
        paint.setColor(Color.RED)
        paint.setStyle(Paint.Style.FILL)
        var ovalpath=Path()
        var rect=RectF(50f,50f,200f,500f)
        ovalpath.addOval(rect,Path.Direction.CCW)
        var region2=Region()
        // region2.union(Rect(50,50,200,200))
         region2.setPath(ovalpath,Region(50,50,200,200))
        drawRegion(canvas,region2,paint)
    }
    fun regionOp(canvas: Canvas,paint: Paint){
        //构造一条椭圆路径
        paint.setColor(Color.GREEN)
        paint.setStyle(Paint.Style.FILL)
        var ovalpath=Path()
        var rect=RectF(50f,50f,200f,500f)
        ovalpath.addOval(rect,Path.Direction.CCW)
        var region2=Region()
        region2.op(Region(50,50,200,200),Region.Op.DIFFERENCE)
        drawRegion(canvas,region2,paint)
    }
    fun regionOP2(canvas: Canvas,paint: Paint){
        paint.setColor(Color.GREEN)
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=2f
        var rect1=Rect(100,100,400,200)
        var rect2=Rect(200,0,300,300)
        canvas.drawRect(rect1,paint)
        canvas.drawRect(rect2,paint)
        var region1=Region(rect1)
        var region2=Region(rect2)
        region1.op(region2,Region.Op.DIFFERENCE)
        paint.setColor(Color.GREEN)
        paint.style=Paint.Style.FILL
        drawRegion(canvas,region1,paint)
    }



    //区域region
    fun drawRegion(canvas: Canvas,rgn:Region,paint: Paint){
        var iter=RegionIterator(rgn)
        var r=Rect()
        while (iter.next(r)){
            canvas.drawRect(r,paint)
        }
    }

}