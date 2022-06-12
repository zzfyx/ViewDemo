package com.hcmes.viewdemo.l5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.hcmes.viewdemo.databinding.ActivityLin5Binding

class Lin5Activity : AppCompatActivity() {
    lateinit var bind:ActivityLin5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         bind=  ActivityLin5Binding.inflate(LayoutInflater.from(this))
         setContentView(bind.root)

    }
}