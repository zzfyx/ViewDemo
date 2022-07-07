package com.hcmes.viewdemo.l11;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RectRefreshSurfaceView extends SurfaceView {

    private Paint mPaint;

    public RectRefreshSurfaceView(Context context) {
        super(context);
        init();
    }

    public RectRefreshSurfaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectRefreshSurfaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(30);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                drawText(holder);
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
        new Thread(new Runnable() {
            @Override
            public void run() {


               /* while (true) {



               *//*     Canvas canvass = holder.lockCanvas();
                    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    canvass.drawPaint(mPaint);
                    holder.unlockCanvasAndPost(canvass);*//*

                    Rect dirtyRect = new Rect(0, 0, 1, 1);
                    Canvas canvas = holder.lockCanvas(dirtyRect);
                    Rect canvasRect = canvas.getClipBounds();
                    if (getWidth() == canvasRect.width() && getHeight() == canvasRect.height()) {
                        canvas.drawColor(Color.BLACK);
                        holder.unlockCanvasAndPost(canvas);
                    } else {
                        canvas.drawColor(Color.BLACK);
                        holder.unlockCanvasAndPost(canvas);
                        break;
                    }
                }*/
                //先进行清屏操作
                while (true) {
                    Rect dirtyRect = new Rect(0, 0, 1, 1);
                    Canvas canvas = holder.lockCanvas(dirtyRect);
                    Rect canvasRect = canvas.getClipBounds();
                    if (getWidth() == canvasRect.width() && getHeight() == canvasRect.height()) {
                        canvas.drawColor(Color.BLACK);
                        holder.unlockCanvasAndPost(canvas);
                    } else {
                        holder.unlockCanvasAndPost(canvas);
                        break;
                    }
                }
                for (int i=0;i<10;i++){
                    int itemWidth=50;
                    int itemHeight=50;
                    Rect rect=new Rect(i*itemWidth,0,(i+1)*itemWidth-10,itemHeight);
                    Canvas canvas=holder.lockCanvas(rect);
                    if (canvas!=null){
                        canvas.drawColor(Color.GREEN);


                        canvas.drawText(i+"",i*itemWidth+10,itemHeight/2,mPaint);
                    }
                    holder.unlockCanvasAndPost(canvas);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

              /*  for (int i = 0; i < 10; i++) {
                    if (i == 0) {
                        Canvas canvas = holder.lockCanvas(new Rect(10, 10, 600, 600));
                        canvas.drawColor(Color.RED);
                        holder.unlockCanvasAndPost(canvas);
                    }
                    if (i == 1) {
                        Canvas canvas = holder.lockCanvas(new Rect(30, 30, 570, 570));
                        canvas.drawColor(Color.GREEN);
                        holder.unlockCanvasAndPost(canvas);
                    }
                    if (i == 2) {
                        Canvas canvas = holder.lockCanvas(new Rect(60, 60, 540, 540));
                        canvas.drawColor(Color.BLUE);
                        holder.unlockCanvasAndPost(canvas);
                    }
                    if (i == 3) {
                        Canvas canvas = holder.lockCanvas(new Rect(200, 200, 400, 400));
                        mPaint.setColor(Color.argb(0x3F, 0xFF, 0xFF, 0xFF));
                        canvas.drawCircle(300, 300, 100, mPaint);
                        holder.unlockCanvasAndPost(canvas);
                    }
                    if (i == 4) {
                        Canvas canvas = holder.lockCanvas(new Rect(250, 250, 350, 350));
                        mPaint.setColor(Color.RED);
                        canvas.drawText(i + "", 300, 300, mPaint);
                        holder.unlockCanvasAndPost(canvas);

                        try {
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }*/
            }
        }).start();
    }
}