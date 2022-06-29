package com.hcmes.viewdemo.l10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.hcmes.viewdemo.R;

public class TelescopeView extends View {
    Matrix matrix=new Matrix();
    int FACTOR=3;
    int RADIUS=80;
    ShapeDrawable drawable;
    Bitmap bitmap;
    public TelescopeView(Context context) {
        super(context);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x= (int) event.getX();
        int y= (int) event.getY();
        matrix.setTranslate(RADIUS+(-x*FACTOR),RADIUS+(-y*FACTOR));
        drawable.getPaint().getShader().setLocalMatrix(matrix);
        drawable.setBounds(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
       invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null) {
            Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
             bitmap=  Bitmap.createScaledBitmap(bmp,getWidth(),getHeight(),false);
             Bitmap b=Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()*FACTOR,bitmap.getHeight()*FACTOR,true);
             BitmapShader shader=new BitmapShader(b, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            drawable=new ShapeDrawable(new OvalShape());
            drawable.getPaint().setShader(shader);
            drawable.setBounds(0,0,RADIUS*2,RADIUS*2);
        }
        canvas.drawBitmap(bitmap,0,0,null);
        drawable.draw(canvas);
    }
}
