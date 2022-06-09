package com.hcmes.viewdemo.l3

import android.R.attr
import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import com.hcmes.viewdemo.R
import android.R.attr.animation
import android.animation.ValueAnimator.AnimatorUpdateListener


private val valueAnimator: ValueAnimator?
    get() {
        var animator = ValueAnimator.ofInt(0, 100, 0)
        return animator
    }



class LoadingImageView: AppCompatImageView{
    constructor(context: Context) : super(context){

    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }
    var top:Int?=0
    var mCurrentImgIndex:Int=0
    var mImgCount:Int=3

    fun  init() {
        var animator = valueAnimator!!
        animator.repeatMode=ValueAnimator.RESTART
        animator.repeatCount=ValueAnimator.INFINITE
        animator.duration=2000
        animator.interpolator=DecelerateInterpolator()
        animator.addUpdateListener {
            var value =it.getAnimatedValue() as Int
            setTop((this.top!!.minus(value)))
        }
        animator.setEvaluator(ReverseEvaluator())
        animator.addListener(object:Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
                setImageDrawable(resources.getDrawable(R.drawable.pic1))
            }

            override fun onAnimationEnd(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {
                mCurrentImgIndex++
                when (mCurrentImgIndex%mImgCount){
                    0->   setImageDrawable(resources.getDrawable(R.drawable.pic1))
                    1->   setImageDrawable(resources.getDrawable(R.drawable.pic2))
                    2->   setImageDrawable(resources.getDrawable(R.drawable.pic3))
                }
            }
        })
        animator.start()


    }
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        this.top=top
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator!!.cancel()
    }

    inner class  ReverseEvaluator :TypeEvaluator<Integer>{
        override fun evaluate(fraction: Float, startValue: Integer, endValue: Integer): Integer {
             val startInt=startValue!!
             var len= endValue.toInt()-startInt.toInt()
             var fr=fraction*len
             var end=  (endValue.toInt()-fr).toInt()
            return end  as Integer
        }

    }

}