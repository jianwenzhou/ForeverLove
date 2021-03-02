package com.richinfo.homemodel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.richinfo.homemodel.activity.splash.SplashActivity

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homemodel_activity_test)
    }

    fun startHome(view: View?) {
        val intent = Intent().setClass(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }
}