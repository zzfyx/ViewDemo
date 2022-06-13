package com.hcmes.viewdemo.l5

import android.app.AlertDialog
import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.hcmes.viewdemo.R
import com.hcmes.viewdemo.databinding.ActivityLin5Binding

class Lin5Activity : AppCompatActivity() {
    lateinit var bind:ActivityLin5Binding
    companion object{
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=  ActivityLin5Binding.inflate(LayoutInflater.from(this))
        setContentView(bind.root)
        bind.imageView.setOnClickListener {
            var animatedVectorDrawableCompat=   AnimatedVectorDrawableCompat.create(this, R.drawable.line_animated_verctor)
            bind.imageView.setImageDrawable(animatedVectorDrawableCompat)
            var any= bind.imageView.drawable as Animatable
            any.start()
        }
        bind.animImg.setFocusable(true)
        bind.animImg.isFocusableInTouchMode=true
        bind.animImg.requestFocus()
        bind.animImg.requestFocusFromTouch()
        bind.edit.setOnFocusChangeListener(object:View.OnFocusChangeListener{
            override fun onFocusChange(p0: View, p1: Boolean) {
              if (p0.hasFocus()){
                  var animatedVectorDrawableCompat=   AnimatedVectorDrawableCompat.create(this@Lin5Activity, R.drawable.animated_verctor_search)
                  bind.animImg.setImageDrawable(animatedVectorDrawableCompat)
                  var any= bind.animImg.drawable as Animatable
                  any.start()
              }
            }

        })

    }
}