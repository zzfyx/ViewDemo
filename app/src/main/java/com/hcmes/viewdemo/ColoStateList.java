package com.hcmes.viewdemo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.io.InputStream;

public class ColoStateList {
    public static ColorStateList createColorStateList(int pressed, int normal){ //状态
        int[][] states = new int[][]{};//按下
        states[0] = new int[]{android.R.attr.state_pressed} ;//默认
        states[1] = new int[]{-android.R.attr.state_pressed};

        //状态对应颜色值（按下，默认）
        return new ColorStateList(states, new int[]{pressed,normal});
    }

    public static StateListDrawable createStateListDrawable(int width,int height){
        StateListDrawable stateListDrawable = new StateListDrawable();

        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        Paint paint=shapeDrawable.getPaint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        shapeDrawable.setPadding(1,1,1,1);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.setIntrinsicHeight(height);
        shapeDrawable.setIntrinsicWidth(width);

        ShapeDrawable arcDrawable = new ShapeDrawable(new ArcShape(10,270));
        Paint paint2=shapeDrawable.getPaint();
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(1);
        arcDrawable.setIntrinsicHeight(height);
        arcDrawable.setIntrinsicWidth(width);

        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, arcDrawable);
        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, shapeDrawable);

        stateListDrawable.setBounds(0,0,width,height);
        return stateListDrawable;
    }

    //GradientDrawable只是用来确定形状的
    public static StateListDrawable createGradientDrawable(int pressed, int normal){
        StateListDrawable stateListDrawable = new StateListDrawable();


        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(3);
        gradientDrawable.setColor(pressed);
        gradientDrawable.setSize(10,10);

        GradientDrawable gradientDrawable2=new GradientDrawable();
        gradientDrawable2.setShape(normal);
        gradientDrawable2.setCornerRadius(3);
        gradientDrawable2.setColor(Color.RED);
        gradientDrawable2.setSize(10,10);
        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed},gradientDrawable2);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},gradientDrawable);



        return stateListDrawable;
    }
    public static  void createBitmDrawable(Context context){
         InputStream inputStream=null;
        BitmapDrawable bitmapDrawable=new BitmapDrawable(context.getResources(),inputStream);
    }

}
