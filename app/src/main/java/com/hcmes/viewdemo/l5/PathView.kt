package com.hcmes.viewdemo.l5

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import com.hcmes.viewdemo.R

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
        mArrayBmp= BitmapFactory.decodeResource(resources, R.mipmap.arraw)
        var valueAnimator=  ValueAnimator.ofFloat(0f,1f).setDuration(2000)
        valueAnimator.addUpdateListener {
            currentFloat=it.getAnimatedValue() as Float
            invalidate()
        }
        valueAnimator.repeatCount=ValueAnimator.INFINITE
       // valueAnimator.interpolator=BounceInterpolator()
        valueAnimator.start()

    }

     var pos =FloatArray(2)
     var tan=FloatArray(2)
  var   mArrayBmp:Bitmap?=null
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint= Paint()
        paint.setColor(Color.BLACK)
        paint.strokeWidth=2f
        paint.style=Paint.Style.STROKE
        var path=Path()
        canvas.translate(100f,100f)
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


/*        一般获取最外面的轮廓
        path.addRect(-50f,-50f,50f,50f,Path.Direction.CCW)
        path.addRect(-100f,-100f,100f,100f,Path.Direction.CCW)
        path.addRect(-120f,-120f,120f,120f,Path.Direction.CW)
        canvas.drawPath(path,paint)
        var measure=PathMeasure(path,false)

        do {
         var len=   measure.length;
            Log.i("qijian","len="+len)
        }while (measure.nextContour())*/


        path.addCircle(10f,10f,mArrayBmp!!.width.toFloat(),Path.Direction.CW)
        canvas.drawPath(path,paint)
        var dst=Path()
       // dst.lineTo(10f,100f)
        var pathMeasure=PathMeasure(path,false)


        var stop=currentFloat*pathMeasure.length
        var start=stop-(0.5f-Math.abs(currentFloat-0.5f))*pathMeasure.length

        //最后一个函数，如果startWithmoveTo为true，路径起始点移动到新添加路径的起始点，否则使用之前的路径直接连过来，不会调用moveto

        pathMeasure.getSegment(start,stop,dst,true)
        paint.setColor(Color.RED)
        canvas.drawPath(dst,paint)
        //旋转箭头图片
       /* pathMeasure.getPosTan(stop,pos,tan)
        var degrees=(Math.atan2(tan.get(1).toDouble(), tan.get(0).toDouble())*180f/Math.PI).toFloat()
        var matrix=Matrix();
        matrix.postRotate(degrees,mArrayBmp!!.width/2f,mArrayBmp!!.height/2f)
        matrix.postTranslate(pos[0]-mArrayBmp!!.width/2f,pos[1]-mArrayBmp!!.height/2f)

        canvas.drawBitmap(mArrayBmp!!,matrix,paint)*/

        //计算角方位
        var matrix=Matrix();
        pathMeasure.getMatrix(stop,matrix,PathMeasure.POSITION_MATRIX_FLAG or PathMeasure.TANGENT_MATRIX_FLAG)
        matrix.preTranslate(-mArrayBmp!!.width/2f,-mArrayBmp!!.height/2f)
        canvas.drawBitmap(mArrayBmp!!,matrix,paint)
    }
}