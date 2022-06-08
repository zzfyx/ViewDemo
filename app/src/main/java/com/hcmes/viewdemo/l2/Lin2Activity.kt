package com.hcmes.viewdemo.l2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.*
import com.hcmes.viewdemo.R
import com.hcmes.viewdemo.databinding.ActivityL2Binding

class Lin2Activity : AppCompatActivity() {
   lateinit var binding:ActivityL2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityL2Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        //xml文件用法
        var animation= AnimationUtils.loadAnimation(this,R.anim.scale)
        binding.imageView.startAnimation(animation)

 /*       android:fromXScale="0.0"
        android:toXScale="1.4"
        android:fromYScale="0.0"
        android:toYScale="1.4"
        android:duration="1700"
        android:pivotX="50%"
        android:pivotY="50%"
        android:repeatMode="reverse"
        android:repeatCount="10"
        android:fillAfter="true"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"*/
        var scaleAnimation=ScaleAnimation(0.0f,1.4f,0f,1.4f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        scaleAnimation.fillAfter=true
        scaleAnimation.duration=1000
        scaleAnimation.repeatCount=Animation.INFINITE
        scaleAnimation.repeatMode=Animation.REVERSE

        var rotate=RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)

        rotate.fillAfter=true
        rotate.duration=1000
        rotate.repeatCount=Animation.INFINITE
        rotate.repeatMode=Animation.REVERSE

        var set=AnimationSet(true)
        set.addAnimation(scaleAnimation)
        set.addAnimation(rotate)

        var tran=TranslateAnimation(Animation.ABSOLUTE,-80f,Animation.ABSOLUTE,80f,Animation.ABSOLUTE,-80f,Animation.ABSOLUTE,80f)
        tran.fillAfter=true
        tran.duration=1000
        tran.repeatCount=Animation.INFINITE
        tran.repeatMode=Animation.REVERSE
        set.addAnimation(tran)
        binding.imageView.startAnimation(set)

    }
}