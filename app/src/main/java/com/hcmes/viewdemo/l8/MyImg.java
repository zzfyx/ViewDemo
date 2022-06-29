package com.hcmes.viewdemo.l8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hcmes.viewdemo.R;

public class MyImg extends ImageView {
    float mBorderWidth=5;
    private RectF rectF=new RectF();
    private float focusWidth=200;
    private float focusHeight=200;
    private int mBorderColor = 0xAA808080; //焦点框的边框颜色
    private Paint mBorderPaint;
    private Path mFocusPath = new Path();
    private int mMaskColor = 0xAF000000;   //暗色
    private Bitmap mBitmap;
    private PointF mFocusMidPoint;

    public MyImg(Context context) {
        super(context);
    }

    public MyImg(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width=  getWidth()/2;
        int height=  getHeight()/2;
        rectF.left=width-focusWidth;
        rectF.right=width+focusWidth;
        rectF.top=height-focusHeight;
        rectF.bottom=height+focusHeight;
        mBorderPaint = new Paint();
        mBorderPaint.setColor(mBorderColor);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        mFocusMidPoint = new PointF(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
     //   super.onDraw(canvas);
       // mFocusPath.addRect(rectF, Path.Direction.CCW);
      float radius = Math.min((rectF.right - rectF.left) / 2, (rectF.bottom - rectF.top) / 2);

        mFocusPath.addCircle(mFocusMidPoint.x,mFocusMidPoint.y,radius, Path.Direction.CCW);
        canvas.save();
        canvas.clipRect(0, 0, getWidth(), getHeight());
        canvas.clipPath(mFocusPath, Region.Op.DIFFERENCE);
        canvas.drawColor(mMaskColor);
        canvas.restore();


        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setAntiAlias(true);
        canvas.drawPath(mFocusPath, mBorderPaint);
        mFocusPath.reset();


    }



}
