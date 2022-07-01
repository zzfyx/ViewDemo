package com.hcmes.viewdemo.l10

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import com.hcmes.viewdemo.R
import com.hcmes.viewdemo.databinding.ActivityLin10Binding

class Lin10Activity : AppCompatActivity() {
    lateinit var binding: ActivityLin10Binding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=  ActivityLin10Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        //shape标签对应 GradientDrawable
        GradientDrawable()
        ShapeDrawable()
        var option=BitmapFactory.Options()
         option.inDensity=1
         option.inTargetDensity=1
        var bitmap=BitmapFactory.decodeResource(resources, R.drawable.bitmap,option)
        Log.d("bitmapsize",bitmap.width.toString()+"--"+bitmap.height.toString()+"--"+bitmap.byteCount.toString())

        var file=  Environment.getExternalStorageDirectory();
        var path= file.absolutePath+"/bitmap.png";
        var bmp=BitmapFactory.decodeFile(path);

        Log.d("bitmapsize",bmp.width.toString()+"--"+bmp.height.toString()+"--"+bmp.byteCount.toString())
        binding.imageView3.setBackgroundDrawable(BitmapDrawable(resources,bmp))

       var matrix= Matrix()
        matrix.setScale(10f,1f)
       var b2= Bitmap.createBitmap(bitmap,bitmap.width/3,bitmap.height/3,bitmap.width/3,bitmap.height/3,matrix,true)
        binding.imageView4.setBackgroundDrawable(BitmapDrawable(resources,b2))
    }
}