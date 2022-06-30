package com.hcmes.viewdemo.m1

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.RectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.hcmes.viewdemo.R
import com.hcmes.viewdemo.l10.CustomDrawable

class M1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m1)
        var bitmap=  BitmapFactory.decodeResource(resources,R.drawable.a)
        var c=CustomDrawable(bitmap);

    }
}