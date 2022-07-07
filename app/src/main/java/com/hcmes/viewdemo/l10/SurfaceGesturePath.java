package com.hcmes.viewdemo.l10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceGesturePath extends SurfaceView {

    private Paint mPaint;
    private Path mPath;

    public SurfaceGesturePath(Context context) {
        super(context);
        init();
    }

    public SurfaceGesturePath(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceGesturePath(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

   void  init(){
         //setWillNotDraw(false);
         mPaint = new Paint();
         mPaint.setStyle(Paint.Style.STROKE);
         mPaint.setStrokeWidth(3);
         mPaint.setColor(Color.RED);
          mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
             mPath.moveTo(event.getX(),event.getY());
        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
            mPath.lineTo(event.getX(),event.getY());
            postInvalidate();
        }
        drawCanvas();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawPath(mPath, mPaint);
    }
    //方式二
    private void drawCanvas(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SurfaceHolder surfaceHolder=getHolder();
                Canvas canvas=surfaceHolder.lockCanvas();
                canvas.drawPath(mPath,mPaint);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }).start();

    }
}
