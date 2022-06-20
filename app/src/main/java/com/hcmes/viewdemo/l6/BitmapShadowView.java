package com.hcmes.viewdemo.l6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hcmes.viewdemo.R;
//阴影效果
public class BitmapShadowView extends View {
    Bitmap mBitmap,mAlphaBmp;
    private Paint mPaint;
    public BitmapShadowView(Context context) {
        super(context);
    }
    public BitmapShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint=new Paint();
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.resoucesate);
        mAlphaBmp=mBitmap.extractAlpha();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=200;
        int height=width*mAlphaBmp.getWidth()/mAlphaBmp.getHeight();
        mPaint.setColor(Color.GRAY);
        canvas.drawBitmap(mAlphaBmp,null,new Rect(10,10,width,height),mPaint);

        canvas.translate(width,0);
        mPaint.setColor(Color.BLACK);
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(mAlphaBmp,null,new Rect(10,10,width,height),mPaint);

        //绘制原图像
        canvas.translate(-5,-5);
        mPaint.setMaskFilter(null);
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);


        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR));

        canvas.drawRect(new Rect(0,0,getWidth()/2,getHeight()/2),mPaint);
    }
}
