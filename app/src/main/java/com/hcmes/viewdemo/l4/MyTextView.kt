package com.hcmes.viewdemo.l4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.hcmes.viewdemo.R

/**
 * TODO: document your custom view class.
 */
class MyTextView : TextView {

   var CharText:Char?=null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

    }

    fun setCharText(char: Char){
        CharText=char
        setText(char.toString())
    }
    fun setCharText(char: Int){
        setText(char.toChar().toString())
    }

}