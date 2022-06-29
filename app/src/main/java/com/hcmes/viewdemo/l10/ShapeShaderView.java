package com.hcmes.viewdemo.l10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hcmes.viewdemo.R;

public class ShapeShaderView extends View {
    private ShapeDrawable mShapeDrawable;
    public ShapeShaderView(Context context) {
        super(context);
    }

    public ShapeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mShapeDrawable=new ShapeDrawable(new RectShape());
        mShapeDrawable.setBounds(new Rect(100,100,300,300));
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.pic_1);
        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mShapeDrawable.getPaint().setShader(bitmapShader);
    }

    public ShapeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mShapeDrawable.draw(canvas);
    }
}
