package com.hcmes.viewdemo.l6;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

//闪光文字效果
public class ShimmerTextView extends AppCompatTextView {

    private final Paint mPaint;
    private int mDx;
    private LinearGradient linearGradient;

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = getPaint();
        int len= (int) mPaint.measureText(getText().toString());
        createAnim(len);
        createLinearGradient(len);
    }

    private void createLinearGradient(int length) {
        ValueAnimator animator=   ValueAnimator.ofInt(0,2*length);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mDx = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();
    }

    private void createAnim(int len) {
        linearGradient = new LinearGradient(-len,0,0,0,new int[]{getCurrentTextColor(),0xff00ff00 , getCurrentTextColor() },new float[]{0,0.5f,1f}, Shader.TileMode.CLAMP);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Matrix matrix=new Matrix();
        matrix.setScale(mDx,0);
        matrix.setTranslate(mDx,0);
        linearGradient.setLocalMatrix(matrix);
        mPaint.setShader(linearGradient);
        RadialGradient mRadialGradient;

        super.onDraw(canvas);
    }
}
