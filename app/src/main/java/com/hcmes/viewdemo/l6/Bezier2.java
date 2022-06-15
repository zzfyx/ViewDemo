package com.hcmes.viewdemo.l6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by trc on 17/4/26.
 * 贝塞尔曲线
 */

public class Bezier2  extends View {
    //
    private Paint mPaint;
    //

    private int centerX, centerY;
    // 起点 终点 第一个控制地那 第二个控制点
    private PointF start, end, control1, control2;
    //
    private boolean mode = true;

    public Bezier2(Context context) {
        this(context, null);

    }

    public Bezier2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control1.x = centerX;
        control1.y = centerY - 100;
        control2.x = centerX;
        control2.y = centerY - 100;

    }
  boolean click=false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            if (Math.abs(event.getX()-control1.x)<=15&&Math.abs(event.getY()-control1.y)<=15){
                mode=true;
                click=true;
            }else if (Math.abs(event.getX()-control2.x)<=15&&Math.abs(event.getY()-control2.y)<=15){
                mode=false;
                click=true;
            }else{
                click=false;
            }
        }else   if (event.getAction()==MotionEvent.ACTION_MOVE){
            if (click){
                // 根据触摸位置更新控制点，并提示重绘
                if (mode) {
                    control1.x = event.getX();
                    control1.y = event.getY();
                } else {
                    control2.x = event.getX();
                    control2.y = event.getY();
                }
                invalidate();
            }
        }else{

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(30);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);
        mPaint.setStrokeWidth(2);
        canvas.drawText("x:"+String.valueOf(control1.x).substring(0,4)+"y:"+ String.valueOf(control1.y).substring(0,4),control1.x,control1.y-40,mPaint);
        canvas.drawText("x:"+String.valueOf(control2.x).substring(0,4)+"y:"+ String.valueOf(control2.y).substring(0,4),control2.x,control2.y-40,mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y,control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y,end.x, end.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x,control2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }
}