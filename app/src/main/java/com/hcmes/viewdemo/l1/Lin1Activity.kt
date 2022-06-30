package com.hcmes.viewdemo.l1

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.RectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.hcmes.viewdemo.R

class Lin1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l1)

        findViewById<ImageView>(R.id.imageView).post({
            val stateListDrawable = StateListDrawable()

            val shapeDrawable = ShapeDrawable(RectShape())
            shapeDrawable.paint.setColor(Color.BLUE)
            shapeDrawable.paint.strokeWidth=10f
            shapeDrawable.setPadding(1,1,1,1)
            shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
            shapeDrawable.setBounds(0,0,200,200)



            val arcDrawable = ShapeDrawable(ArcShape(10f, 270f))
            arcDrawable.getPaint().setStyle(Paint.Style.FILL);
            arcDrawable.paint.setColor(Color.RED)
            arcDrawable.setBounds(0,0,200,200)

            stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), arcDrawable)
            stateListDrawable.addState(intArrayOf(android.R.attr.state_empty), shapeDrawable)
            stateListDrawable.setBounds(0,0,200,200)
            findViewById<ImageView>(R.id.imageView).setImageDrawable(shapeDrawable)

        })

    }
}