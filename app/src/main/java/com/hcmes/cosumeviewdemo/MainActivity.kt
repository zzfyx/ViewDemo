package com.hcmes.cosumeviewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hcmes.cosumeviewdemo.l1.Lin1Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent=Intent(baseContext,Lin1Activity::class.java)
        startActivity(intent)
    }
}