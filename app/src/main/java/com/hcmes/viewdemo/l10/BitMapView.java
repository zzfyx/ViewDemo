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
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hcmes.viewdemo.DensityUtil;
import com.hcmes.viewdemo.R;


public class BitMapView extends View {

    private Bitmap bitmap,bitmaplogo;
    private Paint paint;
    private int weight;
    private int height;

    public BitMapView(Context context) {
        super(context);
    }

    public BitMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public BitMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Bitmap bitmap2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bbb);
        bitmap= Bitmap.createScaledBitmap(bitmap2, DensityUtil.getWindowWidth(getContext()),DensityUtil.getWindowHight(getContext()),true);
        weight = bitmap.getWidth();
        height = bitmap.getHeight();
        paint = new Paint();
        paint.setTextSize(30);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.dog);
        bitmaplogo= Bitmap.createScaledBitmap(bitmap3, bitmap3.getWidth()/5,bitmap3.getHeight()/5,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap,0,0,null);
        canvas.drawBitmap(bitmaplogo,weight-bitmaplogo.getWidth()-5,height-bitmaplogo.getHeight()-5,paint);
        String text="2022年7月4日17:28:50";
        Rect rectF=new Rect();
        paint.getTextBounds(text,0,text.length(),rectF);
        canvas.drawText("2022年7月4日17:28:50",weight-(rectF.right-rectF.left)-10,height-(rectF.bottom-rectF.top),paint);
    }
}
