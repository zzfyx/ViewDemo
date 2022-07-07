package com.hcmes.viewdemo.l10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class DoubleBufferingView extends SurfaceView {
    Paint mPaint;
    private SurfaceHolder mSurfaceHolder;


    public DoubleBufferingView(Context context) {
        super(context);
    }
    public DoubleBufferingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        drawText(holder) ;
                    }
                }).start();

            }



            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }
    private void drawText(SurfaceHolder holder) {
        for (int i = 0; i < 10; i++) {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawText(i + "", i * 30, 50, mPaint);
            }
            holder.unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
