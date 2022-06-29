package com.hcmes.viewdemo.l7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.hcmes.viewdemo.databinding.ActivityLin6Binding
import com.hcmes.viewdemo.databinding.ActivityLin7Binding

class Lin7Activity : AppCompatActivity() {
    lateinit var  bind: ActivityLin7Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityLin7Binding.inflate(LayoutInflater.from(this))
        setContentView(bind.root)
    }
}