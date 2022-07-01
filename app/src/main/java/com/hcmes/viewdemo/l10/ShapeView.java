package com.hcmes.viewdemo.l10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ShapeView extends View {

    private ShapeDrawable mShapeDra;

    public ShapeView(Context context) {
        super(context);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE,null);
     /*   mShapeDra = new ShapeDrawable(new RectShape());
        mShapeDra = new ShapeDrawable(new OvalShape());
        mShapeDra = new ShapeDrawable(new ArcShape(0,300));
        mShapeDra = new ShapeDrawable(new RoundRectShape(new float[]{12,12,12,12,0,0,0,0},new RectF(6,6,6,6),new float[]{50,12,0,12,50,0,0}));

          Path path=new Path();
          path.moveTo(0,0);
          path.lineTo(100,0);
          path.lineTo(100,100);
          path.lineTo(0,100);
          path.close();*/

        /*  Rect rect1=new Rect(50,0,90,150);
          Rect rect2=new Rect(0,50,250,100);
          Region region=new Region(rect1);
          Region region2=new Region(rect2);
         region.op(region2,Region.Op.XOR);
         mShapeDra = new ShapeDrawable(new RegionShape(region));*/


    //    mShapeDra = new ShapeDrawable(new PathShape(path,100,100));


       /* mShapeDra.setBounds(new Rect(50,50,200,100));
        mShapeDra.getPaint().setColor(Color.YELLOW);*/
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      //  mShapeDra.draw(canvas);
    }
}
