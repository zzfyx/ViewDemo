package com.hcmes.viewdemo.l10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.hcmes.viewdemo.R;

public class AnimationSurfaceView extends SurfaceView {
    private final int BITMAP_STEP = 1; //背景画布移动速度
    private SurfaceHolder mSurfaceHolder;
    private boolean flag;
    //屏幕宽高
    private float mSurfaceWidth;
    private float mSurfaceHeight;
    private Bitmap bitmap_bg;// 背景图
    private Thread thread;
    private Canvas mCanvas;
    private int mBitposX; //开始绘制的图片的x坐标
    private Paint paint;
    private ColorMatrix matrix;
    private State mState = State.LEFT; //默认最开始向左移动
    public AnimationSurfaceView(Context context) {
        super(context);
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                flag = true;
                startAnimation();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }

    private void startAnimation(){
        mSurfaceWidth=getWidth();
        mSurfaceHeight=getHeight();
        paint = new Paint();
        matrix = new ColorMatrix();

        //黑白效果
       /* paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0,0,0,1,0

        })));*/
        int mWidth=(int)(mSurfaceWidth*3/2);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
        bitmap_bg = Bitmap.createScaledBitmap(bitmap, mWidth, (int) mSurfaceHeight, true);
        //huitu

        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    mCanvas = mSurfaceHolder.lockCanvas();
                    drawView();
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * 进行绘制
     */
    private void drawView() {
        if (mCanvas==null)return;
        matrix.setSaturation(mBitposX);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);//清空屏幕
        mCanvas.drawBitmap(bitmap_bg, mBitposX, 0, paint);//绘制当前屏幕背景

        switch (mState) {
            case LEFT:
                mBitposX -= BITMAP_STEP; //画布左移
                break;
            case RIGHT:
                mBitposX += BITMAP_STEP; //画布右移
                break;
            default:
                break;
        }

        if (mBitposX <= -mSurfaceWidth / 2) {
            mState = State.RIGHT;
        }
        if (mBitposX >=0) {
            mState = State.LEFT;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        flag=false;
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
    }

    //背景移动状态
    private enum State {
        LEFT, RIGHT
    }
}
