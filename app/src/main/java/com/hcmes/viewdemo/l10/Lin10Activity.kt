package com.hcmes.viewdemo.l10

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
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
        binding.addShapeCorner.setOnClickListener {
         var drawable=   binding.addShapeCorner.background as GradientDrawable
            drawable.cornerRadius=20f
        }

    }
}