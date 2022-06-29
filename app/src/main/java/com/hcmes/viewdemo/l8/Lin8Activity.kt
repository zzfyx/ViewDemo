package com.hcmes.viewdemo.l8

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.hcmes.viewdemo.databinding.ActivityLin6Binding
import com.hcmes.viewdemo.databinding.ActivityLin8Binding
import android.R.drawable

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape


class Lin8Activity : AppCompatActivity() {
    lateinit var  bind: ActivityLin8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityLin8Binding.inflate(LayoutInflater.from(this))
        setContentView(bind.root)
        var a=ShapeDrawable()
         // 使用path 设置一个圆形 然后画布修剪出来，再画上bitmap
         //使用PorterDuffXfermode 裁切
         //使用BitmapShader 着色器配合Matrix 缩放
         //https://blog.csdn.net/Reoger/article/details/81394579?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-2-81394579-blog-77750546.pc_relevant_antiscanv2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-2-81394579-blog-77750546.pc_relevant_antiscanv2&utm_relevant_index=5
    }

   /* @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmapShader == null) {
            Bitmap bitmap = drawableToBitmap(getDrawable());
            //CLAMP表示，当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间。
            //REPEAT表示，当我们绘制的图形尺寸大于Bitmap尺寸时，会用Bitmap重复平铺整个绘制的区域
            //MIRROR与REPEAT类似，当绘制的图形尺寸大于Bitmap尺寸时，MIRROR也会用Bitmap重复平铺整个绘图区域，与REPEAT不同的是，两个相邻的Bitmap互为镜像。
            mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //计算缩放比例
            mScale = (mRadius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());
            mMatrix.setScale(mScale, mScale);
        }
        //设置图片缩放
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }*/

    fun drawableToBitmap(drawable: Drawable):Bitmap{
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        var bitmap=Bitmap.createBitmap(drawable.intrinsicWidth,drawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        var canvas=Canvas(bitmap)
        drawable.setBounds(0,0,drawable.intrinsicWidth,drawable.intrinsicHeight)
        drawable.draw(canvas)
        return  bitmap
    }
}