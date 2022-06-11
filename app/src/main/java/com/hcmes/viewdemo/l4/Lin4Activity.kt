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
import android.animation.*
import android.util.Property
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationSet
import android.widget.LinearLayout
import android.widget.TextView
import com.hcmes.viewdemo.R


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
        bind.tv.setOnClickListener{
            var animator=    AnimatorInflater.loadAnimator(this, R.animator.animator) as ValueAnimator
            animator.addUpdateListener {
                var value=    it.getAnimatedValue() as Int
                bind.tv.layout(value,value,value+bind.tv.width,value+bind.tv.height)
            }
           // animator.start()
            var objanimator=    AnimatorInflater.loadAnimator(this, R.animator.objanimator) as ObjectAnimator
            objanimator.target=bind.tv
            //objanimator.start()

         var anset=AnimatorSet()
            anset.playTogether(animator)
            anset.play(objanimator)
            anset.start()
        }
        bind.tv2.setOnClickListener {
            var propertyValuesHolder=PropertyValuesHolder.ofFloat("Rotation",60f,-60f,40f,-40f,-20f,20f,10f,-10f,0f)
            var alphaPropertyValuesHolder=PropertyValuesHolder.ofFloat("alpha",0.1f,1f,0.1f,1f);
            var holderAnimator=  ObjectAnimator.ofPropertyValuesHolder(bind.tv2,propertyValuesHolder,alphaPropertyValuesHolder);
            holderAnimator.setDuration(3000)
            holderAnimator.start()
        }
        //a-z
        bind.tv3.setOnClickListener{
            var charHolder=PropertyValuesHolder.ofObject("CharText",CharEvaluator(),'A'.toInt(),'Z'.toInt())
            var animator=ObjectAnimator.ofPropertyValuesHolder( bind.tv3,charHolder)
            animator.setDuration(3000)
            animator.setInterpolator(AccelerateInterpolator())
            animator.start()
        }
        bind.tv4.setOnClickListener{
            var frame0=Keyframe.ofFloat(0f,0f)
            var frame1=Keyframe.ofFloat(0.1f,-20f)
            var frame2=Keyframe.ofFloat(0.2f,20f)
            var frame3=Keyframe.ofFloat(0.3f,-20f)
            var frame4=Keyframe.ofFloat(0.4f,20f)
            var frame5=Keyframe.ofFloat(0.5f,-20f)
            var frame6=Keyframe.ofFloat(0.6f,20f)
            var frame7=Keyframe.ofFloat(0.7f,-20f)
            var frame8=Keyframe.ofFloat(0.8f,0f)
            var frame9=Keyframe.ofFloat(1f,0f)
            var charHolder=PropertyValuesHolder.ofKeyframe("rotation",frame0,frame1,frame2,frame3,frame4,frame5,frame6,frame7,frame8,frame9)
            var animator=ObjectAnimator.ofPropertyValuesHolder( bind.tv4,charHolder)
            animator.setDuration(1000)
            animator.setInterpolator(AccelerateInterpolator())
            animator.start()



            var newview=TextView(this@Lin4Activity)
            newview.text="AddView"
            newview.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
           newview.setBackgroundColor(resources.getColor(R.color.design_default_color_primary))
            bind.rootView1.addView(newview,0)

        }



        var transition=LayoutTransition()
        var animInt=ObjectAnimator.ofFloat(null,"rotation",90f,180f)
            .setDuration(transition.getDuration(LayoutTransition.APPEARING))
        transition.setAnimator(LayoutTransition.APPEARING,animInt)
        bind.rootView1.layoutTransition=transition

//NineOldAndroids
    }

    class CharEvaluator:TypeEvaluator<Int> {
        override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
           var startInt= startValue!!.toInt()
            var endInt=endValue!!.toInt()
            var curInt=startInt+fraction*(endInt-startInt)
            return curInt.toInt()
        }

    }
  /*  class CharEvaluator:TypeEvaluator<Char> {
        override fun evaluate(fraction: Float, startValue: Char?, endValue: Char?): Char {
           var startInt= startValue!!.toInt()
            var endInt=endValue!!.toInt()
            var curInt=startInt+fraction*(endInt-startInt)
            var ch=curInt.toInt().toChar();
            return 'F'
        }

    }*/
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