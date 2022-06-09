package com.hcmes.viewdemo.l3

import android.animation.*
import android.animation.ValueAnimator.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.hcmes.viewdemo.R
import com.hcmes.viewdemo.databinding.ActivityLin3Binding
//属性动画
class Lin3Activity : AppCompatActivity() {
       lateinit  var  binding:ActivityLin3Binding
       lateinit var   animator:ValueAnimator
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLin3Binding.inflate(LayoutInflater.from(this))
            //关闭一定要取消动画，不然activity不能回收
            setContentView(binding.root)
            animator=  ValueAnimator.ofInt(0,400);
            animator.duration=2000
            animator.repeatCount=INFINITE
            animator.repeatMode=RESTART
            animator.addUpdateListener {
                var value= it.getAnimatedValue() as Int
                 binding.button.layout(value,value,value+binding.button.width,binding.button.height+value)

                Log.e("animation",value.toString())
            }
            animator.start()
            binding.button.setOnClickListener({
                Toast.makeText(this, binding.button.x.toString(),Toast.LENGTH_LONG).show()

            })

            val argbAnimator = ValueAnimator.ofInt(0xFF0000FF.toInt(), 0xFFFFFF00.toInt())
            argbAnimator.duration = 3000
            argbAnimator.setEvaluator(ArgbEvaluator())
            argbAnimator.addUpdateListener { animation ->
                val currentvalue = animation.animatedValue as Int
                binding.button.setBackgroundColor(currentvalue)
            }
            argbAnimator.start()
            //ObjectAnimator只能对单个属性进行操作，如果想实现比较复杂的效果就需要用到PropertyValuesHolder了。
            //PropertyValuesHolder 它其中保存了动画过程中所需要操作的属性和对应的值。我们通过ofFloat(Object target, String propertyName, float… values)构造的动画
            //PropertyValuesHolder 它其中保存了动画过程中所需要操作的属性和对应的值。我们通过ofFloat(Object target, String propertyName, float… values)构造的动画
            val rotationHolder = PropertyValuesHolder.ofFloat("Rotation", 50f, -50f, 30f, -30f, 20f, -20f, 10f, -10f, 0f)
            val scaleXHolder = PropertyValuesHolder.ofFloat("ScaleX", 1f, 1.2f)
            val scaleYHolder = PropertyValuesHolder.ofFloat("ScaleY", 1f, 1.2f)
            val animatorHolder=ObjectAnimator.ofPropertyValuesHolder(binding.button2, rotationHolder!!, scaleXHolder, scaleYHolder)
            animatorHolder.duration = 2000
            animatorHolder.interpolator = AccelerateInterpolator()
            animatorHolder.start()
     /*       1.要使用 ObjectAnimator 来构造动画，在要操作的控件中必须存在对应属性的 et 函数，
              而且参数类型必须 构造所使用的。 （）或 oflnt（）函数 致。
            2. set 函数的命名必须采用骆驼拼写法，即 set 每个单词首字母大写，其余字母 写，
              似于 setPropertyName 所对应的属性为 propertyName*/
        //复杂动画可以使用
         var objectAnimator=   ObjectAnimator.ofFloat(binding.button2,"TranslationY",10f,200f)
         var animator=AnimatorSet()
             animator.play(objectAnimator).with(animatorHolder);
            animator.start()
            //AnimatorSet中设置setDuration 会覆盖单独设置的setDuration
            //AnimatorSet 的延时仅针对性地延长AnimatorSet 的激活 时间，对单个动画的延时设置没有影响。 setStartDelay(2000) ;

        //button3
        var s1= ObjectAnimator.ofFloat(binding.button3,"Rotation", 30f, -30f, 20f, -20f, 10f, -10f, 0f)
        s1.repeatCount=INFINITE
        s1.repeatMode=RESTART
        var s2=ObjectAnimator.ofFloat(binding.button3,"Alpha",0.2f,1f)
        s2.repeatCount=INFINITE
        s2.repeatMode=RESTART
        s2.startDelay=2000
        var set= AnimatorSet();
        set.play(s1).with(s2)
        set.duration=1200
        set.interpolator=AccelerateInterpolator()
        set.startDelay=2000
        set.start()
        }

    override fun onDestroy() {
        super.onDestroy()
        animator.cancel()
    }
}