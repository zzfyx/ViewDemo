package com.hcmes.viewdemo.l8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class CircleImageView1 extends ImageView {

    private Paint mPaint;
    private Shape mShape;
    private float mRadius;

    private float[] outerRadii = new float[8];;

    public CircleImageView1(Context context) {
        this(context,null);
    }

    public CircleImageView1(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleImageView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setLayerType(LAYER_TYPE_HARDWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        //注意这里，将画笔的PorterDuffXfermode模式设置为了DST_IN模式！！！
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int min = Math.min(width,height);
        mRadius = min/2;
        setMeasuredDimension(min,min);
        //因为是圆，所以要设置为长宽相等
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed){
            if (mShape == null){
                Arrays.fill(outerRadii,mRadius);
                mShape = new RoundRectShape(outerRadii,null,null);
                //
            }
            mShape.resize(getWidth(),getHeight());
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.getSaveCount();
        canvas.save();
        super.onDraw(canvas);
        if (mShape != null){
            mShape.draw(canvas,mPaint);
        }
        canvas.restoreToCount(saveCount);
    }

    // 这里直接使用Glide库来加载图片，也可以在xml文件或者java代码中设置图片
    public void build(String url){
       // Glide.with(getContext()).load(url).into(this);
    }
}