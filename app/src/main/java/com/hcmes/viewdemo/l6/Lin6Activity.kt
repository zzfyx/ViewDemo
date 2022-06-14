package com.hcmes.viewdemo.l6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.hcmes.viewdemo.R
import com.hcmes.viewdemo.databinding.ActivityLin6Binding

class Lin6Activity : AppCompatActivity() {
    lateinit var  bind:ActivityLin6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         bind= ActivityLin6Binding.inflate(LayoutInflater.from(this))
         setContentView(bind.root)
    }
}