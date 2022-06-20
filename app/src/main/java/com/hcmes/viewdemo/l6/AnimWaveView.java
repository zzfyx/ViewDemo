package com.hcmes.viewdemo.l6;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class AnimWaveView extends View {

    int height;
    int sumCount=100;
    private Paint mPaint;
    private Paint mCirPaint;//绘制 外层圆形
    private Path mPath;
    private int mWaveLength = 800;//单个波纹长度（水平方向）
    private int dx;//波纹水平移动距离
    private int originY = 850;//波纹的原始Y坐标

    public AnimWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);//波纹设置为填充模式。
        mPaint.setColor(0XFF3DDC84);

        mCirPaint = new Paint();
        mCirPaint.setStyle(Paint.Style.STROKE);//外层圆设置为圈圈
        mCirPaint.setStrokeWidth(5);
        mCirPaint.setColor(0XFF3DDC84);

        setLayerType(View.LAYER_TYPE_SOFTWARE , null);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=getMeasuredHeight();
        mWaveLength=getMeasuredWidth();
        originY =height;//重置波纹的纵坐标
        startAnim2();//开启波纹动画
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //剪裁画布，让动画只出现在圆形内，
        Path cirPath = new Path();
        cirPath.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, Path.Direction.CW);
        canvas.clipPath(cirPath);

        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mCirPaint);
        mPath.reset();

        int halfWaveLength = mWaveLength / 2;//这里为什么是一半？请参考上篇”绘画版“博文中 4.2 节
        mPath.moveTo(-mWaveLength + dx, originY);
        for (int i = -mWaveLength; i <= getWidth() + mWaveLength; i += mWaveLength) {
            mPath.rQuadTo(halfWaveLength/2, -100, halfWaveLength, 0);
            mPath.rQuadTo(halfWaveLength/2, 100, halfWaveLength, 0);
        }

        mPath.lineTo(getWidth(), getHeight());//闭合路径，否则将无法绘制波浪颜色
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, mPaint);
        if (originY <= -10) {
            originY =height;//重置波纹的纵坐标

        }
        if (sumCount<=0){
            sumCount=0;
        }
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                originY -= (2 * dx / mWaveLength);//Y坐标也随着X增长
                postInvalidate();
            }
        });
        animator.start();
    }

    private void startAnim2() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)((float)animation.getAnimatedValue()*mWaveLength) ;
                sumCount-=1;
                if ((float)animation.getAnimatedValue()==0.5f){
                    //先定义进度为10 每次进来 mWaveLength/10
                    originY =  sumCount*(mWaveLength/1000);
                }
                invalidate();
            }
        });
        animator.start();

        ValueAnimator animator2 = ValueAnimator.ofInt(mWaveLength, 0);
        animator2.setDuration(10000);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setInterpolator(new LinearInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                originY = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator2.start();
    }


}
