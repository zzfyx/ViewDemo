package com.hcmes.viewdemo.l11

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hcmes.viewdemo.R

class Lin11Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l11)
        val paint = Paint()/*ColorMatrix matrix=new ColorMatrix();
        matrix.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));*//*ColorMatrix matrix=new ColorMatrix();
        matrix.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));*/
        paint.colorFilter =
            PorterDuffColorFilter(Color.RED, PorterDuff.Mode.ADD)
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }
}