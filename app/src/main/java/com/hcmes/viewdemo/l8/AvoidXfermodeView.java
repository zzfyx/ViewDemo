package com.hcmes.viewdemo.l8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hcmes.viewdemo.R;

public class AvoidXfermodeView extends View {

    private Bitmap mBmp;
    private Paint mPaint;

    public AvoidXfermodeView(Context context) {
        super(context);
    }

    public AvoidXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
    }

    public AvoidXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth()/2;
        int height=width*mBmp.getHeight()/mBmp.getWidth();
        int layerID= canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);


        canvas.drawBitmap(mBmp,null,new Rect(0,0,width,height),mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        canvas.drawRect(0,0,width,height,mPaint);
/*        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R. drawable .
                flower) , null , new Rect(O, 0 , width, height) , mPaint);*/


        canvas.restoreToCount(layerID);

    }
}
