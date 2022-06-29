package com.hcmes.viewdemo.l10;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.drawable.shapes.Shape;

import com.hcmes.viewdemo.R;

public class RegionShape extends Shape {

    Region region;
    public RegionShape(Region region) {
        assert(region!=null);
        this.region=region;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        RegionIterator iter=new RegionIterator(region);
        Rect r=new Rect();
        while (iter.next(r)){
            canvas.drawRect(r,paint);
        }
    }


}
