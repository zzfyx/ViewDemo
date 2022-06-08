package com.hcmes.viewdemo.l2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        set.interpolator=CycleInterpolator(1f)
        set.addAnimation(scaleAnimation)
        set.addAnimation(rotate)

        var tran=TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.ABSOLUTE,80f,Animation.ABSOLUTE,480f)
        tran.fillAfter=true
        tran.duration=5000
        tran.repeatCount=0
        tran.fillAfter=true
        tran.interpolator=BounceInterpolator()
        set.addAnimation(tran)
        binding.imageView.startAnimation(set)
       Handler(Looper.myLooper()!!).postDelayed({
                              set.cancel()
            //如果设置的是多次循环的，则停止不了
            rotate.cancel()
        },2000)
        var scale=ScaleAnimation(1f,1.2f,1f,1.2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        scale.fillAfter=true
        scale.interpolator=BounceInterpolator()
        scale.duration=6000
        binding.imageView2.startAnimation(scale)

        var loading=RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        loading.interpolator=LinearInterpolator()
        loading.repeatCount=Animation.INFINITE
        loading.repeatMode=Animation.RESTART
        loading.duration=2000
        binding.imageViewLoading.startAnimation(loading)



        var scan1= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim)
        var scan2= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim)
        var scan3= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim)
        var scan4= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim)
        binding.circlel.startAnimation(scan1)
        scan2.startOffset=600
        binding.circlel2.startAnimation(scan2)
        scan3.startOffset=1200
        binding.circlel3.startAnimation(scan3)
        scan4.startOffset=1800
        binding.circlel4.startAnimation(scan4)
    }
}