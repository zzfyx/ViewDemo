package com.hcmes.viewdemo.l7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View

class NormalGestureTrackView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var mPath= Path()
    var mPaint=Paint()
    init {
        mPaint.color= Color.BLACK
        mPaint.style=Paint.Style.STROKE
        mPaint.strokeWidth=5f

    }
    var mPreX:Float=0f
    var mPreY:Float=0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return   when(event.action){
            MotionEvent.ACTION_DOWN->{
                mPath.moveTo(event.getX(),event.getY())
                mPreX=event.getX();
                mPreY=event.getY();
                true
            }
            MotionEvent.ACTION_MOVE->{
                var endX=(mPreX+event.getX())/2
                var endY=(mPreY+event.getY())/2
                mPath.quadTo(mPreX,mPreY,endX,endY)
                mPreX=event.getX();
                mPreY=event.getY();
                //   mPath.lineTo(event.getX(),event.getY())
                invalidate()
                true
            }
            else-> {
                return super.onTouchEvent(event)
            }
        }
    }
    var mItemWaveLength=1200f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)
        canvas.drawPath(mPath,mPaint)
        mPath.reset()
        var  originY=300f
        var halfWaveLen=mItemWaveLength/2
        mPath.moveTo(-mItemWaveLength,originY)
            var i = (-mItemWaveLength).toInt()
            while (i <= width+mItemWaveLength) {

                mPath.rQuadTo(halfWaveLen/2,-100f,halfWaveLen,0f)
                mPath.rQuadTo(halfWaveLen/2,100f,halfWaveLen,0f)
                i += mItemWaveLength.toInt()
            }
        canvas.drawPath(mPath,mPaint)
        mPath.reset()
    }
}