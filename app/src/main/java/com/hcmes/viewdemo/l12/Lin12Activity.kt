package com.hcmes.viewdemo.l12

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import com.hcmes.viewdemo.R
import com.hcmes.viewdemo.databinding.ActivityLin12Binding

class Lin12Activity : AppCompatActivity() {
    lateinit var binding: ActivityLin12Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLin12Binding.inflate(LayoutInflater.from(this))
        binding.openWindow.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this@Lin12Activity)) { //没有权限，需要申请权限，因为是打开一个授权页面，所以拿不到返回状态的，所以建议是在onResume方法中从新执行一次校验
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                startActivityForResult(intent,100)
            } else {
                WindowDialog(this).show()
            }
        }
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==100){
            WindowDialog(this).show()
        }
    }
}