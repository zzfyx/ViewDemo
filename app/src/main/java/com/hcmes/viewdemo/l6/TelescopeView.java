package com.hcmes.viewdemo.l6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.hcmes.viewdemo.R;

//望远镜效果
public class TelescopeView extends View {

    Bitmap mBitmap;
    RadialGradient  mRadialGradient;
    private int mDx;
    private int mDy;
    private Bitmap mBitmapBG;
    private BitmapShader bitmapShader;
    private Paint mPaint;
    public TelescopeView(Context context) {
        super(context);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint=new Paint();
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //圆形头像
        bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mDx = -1;
                mDy = -1;
                break;

        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆形头像
        Matrix matrix=new Matrix();
        float scale=(float) getWidth()/mBitmap.getWidth();
        matrix.setScale(scale,scale);
        bitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(bitmapShader);
        float half=getWidth()/4;
        canvas.drawCircle(half,half,150,mPaint);
       if (mBitmapBG==null){
            mBitmapBG = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvasbg=new Canvas(mBitmapBG);
            canvasbg.drawBitmap(mBitmap,null,new RectF(0,0,getWidth(),getHeight()),mPaint);
        }
        if (mDx!=-1&&mDy!=-1){
            mPaint.setShader(new BitmapShader(mBitmapBG, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

            canvas.drawCircle(mDx,mDy,150,mPaint);
            mPaint.setShader(new LinearGradient(0,getHeight()/2,getWidth(),getHeight()/2,0xffff0000,0xff00f00,Shader.TileMode.CLAMP));
            canvas.drawText("jianbian",mDx,mDy,mPaint);
        }
        //多色渐变
        if (mRadialGradient == null) {
            int   mRadius = getWidth() /2;
            int [] colors = new int [] { 0xffff0000 , 0xff00ff00 , 0xff0000ff, 0xffffff00};
            float[] stops = new float[]{0f, 0.2f, 0.5f, 1f};
            mRadialGradient = new RadialGradient( getWidth () /2 , getHeight () /2 ,
                    mRadius, colors,stops, Shader .TileMode . REPEAT);
            mPaint.setShader(mRadialGradient);
            canvas.drawCircle(getWidth()/2 , getHeight()/2 , mRadius, mPaint) ;
        }
        GradientDrawable gradientDrawable=new GradientDrawable();
    }
}
