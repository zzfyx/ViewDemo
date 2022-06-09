package com.hcmes.viewdemo.l4

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.hcmes.viewdemo.databinding.ActivityLin4Binding
import android.R.attr.radius
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.AnimationSet


class Lin4Activity : AppCompatActivity(),View.OnClickListener{
    lateinit var   bind:ActivityLin4Binding
    var mIsMenuOpen:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityLin4Binding.inflate(LayoutInflater.from(this))
        setContentView(bind.root)

        bind.menu.setOnClickListener{
            if (!mIsMenuOpen){
                mIsMenuOpen=true;
                openMenu();
            }else{
                mIsMenuOpen=false
                closeMenu()
            }

        }
        bind.item1.setOnClickListener(this)
        bind.item2.setOnClickListener(this)
        bind.item3.setOnClickListener(this)
        bind.item4.setOnClickListener(this)
        bind.item5.setOnClickListener(this)
    }
    fun openMenu(){
        doAnimateOpen(bind.item1 , 0 , 5,300)
        doAnimateOpen(bind.item2 , 1, 5 ,300)
        doAnimateOpen(bind.item3 , 2 , 5 ,300)
        doAnimateOpen (bind.item4, 3 , 5 ,300)
        doAnimateOpen(bind.item5 , 4 , 5,300)
    }
    fun doAnimateOpen(view: View, index:Int, total:Int,radius:Int){
        if (view.visibility!=View.VISIBLE){
            view.visibility=View.VISIBLE
        }
        var degree=Math.toRadians(90.0)/(total-1)*index
        val translationX = (-(radius * Math.sin(degree))).toInt()
        val translationY = (-(radius * Math.cos(degree))).toInt()
        var set= AnimatorSet()
        set.playTogether(
            ObjectAnimator.ofFloat(view,"translationX",0f,translationX.toFloat()),
            ObjectAnimator.ofFloat(view,"translationY",0f,translationY.toFloat()),
            ObjectAnimator.ofFloat(view,"scaleX",0f,1f),
            ObjectAnimator.ofFloat(view,"scaleY" ,0f,1f),
            ObjectAnimator.ofFloat(view,"alpha", 0f,1f))
        set.setDuration(500).start()

    }
    fun doAnimateClose(view: View, index:Int, total:Int,radius:Int){
        var degree= Math.toRadians(90.0) / (total - 1) * index ;
        var translationX = -   (radius * Math . sin (degree))
        var translationY = (radius * Math.cos (degree)) ;
        var set= AnimatorSet()
        set.playTogether(
            ObjectAnimator.ofFloat(view,"translationX", 0f , translationX.toFloat()) ,
            ObjectAnimator.ofFloat(view,"translationY", 0f , translationY.toFloat()) ,
            ObjectAnimator.ofFloat(view,"scaleX", 0f,0.1f),
            ObjectAnimator.ofFloat(view ,"scaleY", 0f,0.1f),
            ObjectAnimator.ofFloat (view,"alpha", 0f, 0.1f)) ;
        set.setDuration(500).start()
    }
    fun closeMenu () {
        doAnimateClose(bind.item1 , 0 , 5,300)
        doAnimateClose(bind.item2 , 1, 5 ,300)
        doAnimateClose(bind.item3 , 2 , 5 ,300)
        doAnimateClose (bind.item4, 3 , 5 ,300)
        doAnimateClose(bind.item5 , 4 , 5,300)

    }

    override fun onClick(p0: View?) {
        Toast.makeText(this,"你单机了"+p0,Toast.LENGTH_SHORT).show()
        mIsMenuOpen=false
        closeMenu()
    }

}